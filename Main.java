package StudyPlanner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Planner planner = new Planner(new SortByDeadline());

        while (true) {
            System.out.println("\n===== SMART PLANNER =====");
            System.out.println("Today's Date: " + LocalDate.now());

            System.out.println("\n1. Show Planner");
            System.out.println("2. Add Plan");
            System.out.println("3. Mark Plan as Done");
            System.out.println("4. Remove Plan");
            System.out.println("5. Clear Finished Tasks");
            System.out.println("6. Exit");

            System.out.print("Choose: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                planner.showPlanner();
            }

            else if (choice == 2) {
                System.out.print("Enter name: ");
                String name = sc.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("Task name cannot be empty.");
                    continue;
                }

                System.out.println("Type: 1-Exam 2-Quiz 3-Assignment 4-Meeting 5-Project 6-Presentation 7-Other");
                System.out.print("Enter type: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Invalid type.");
                    sc.nextLine();
                    continue;
                }

                int type = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter deadline (YYYY-MM-DD): ");
                String inputDate = sc.nextLine().trim();

                try {
                    LocalDate deadline = LocalDate.parse(inputDate);

                    String courseCode = "";
                    boolean lateSubmissionAllowed = false;
                    String location = "";
                    boolean mandatory = false;
                    int progressPercent = 0;
                    String audience = "";

                    if (isAcademicType(type)) {
                        courseCode = handleOptionalCourseCode(sc, planner);
                    }

                    if (type == 3) {
                        lateSubmissionAllowed = askYesNo(sc, "Is late submission allowed? (yes/no): ");
                    }

                    if (type == 4) {
                        System.out.print("Enter location: ");
                        location = sc.nextLine().trim();
                        mandatory = askYesNo(sc, "Is it mandatory? (yes/no): ");
                    }

                    if (type == 5) {
                        progressPercent = askProgress(sc);
                    }

                    if (type == 6) {
                        System.out.print("Enter location: ");
                        location = sc.nextLine().trim();

                        System.out.print("Enter audience: ");
                        audience = sc.nextLine().trim();
                    }

                    Task task = TaskFactory.createTask(
                            type,
                            name,
                            deadline,
                            courseCode,
                            lateSubmissionAllowed,
                            location,
                            mandatory,
                            progressPercent,
                            audience);

                    planner.addTask(task);

                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                }
            }

            else if (choice == 3) {
                System.out.print("Enter index: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid index.");
                    sc.nextLine();
                    continue;
                }

                int index = sc.nextInt();
                sc.nextLine();
                planner.markTaskDone(index);
            }

            else if (choice == 4) {
                System.out.print("Enter index: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid index.");
                    sc.nextLine();
                    continue;
                }

                int index = sc.nextInt();
                sc.nextLine();
                planner.removeTask(index);
            }

            else if (choice == 5) {
                planner.clearFinishedTasks();
            }

            else if (choice == 6) {
                System.out.println("Goodbye!");
                break;
            }

            else {
                System.out.println("Invalid menu choice.");
            }
        }

        sc.close();
    }

    private static boolean isAcademicType(int type) {
        return type == 1 || type == 2 || type == 3 || type == 5;
    }

    private static boolean askYesNo(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("yes"))
                return true;
            if (input.equals("no"))
                return false;

            System.out.println("Please type yes or no.");
        }
    }

    private static int askProgress(Scanner sc) {
        while (true) {
            System.out.print("Enter current progress percentage (0-100): ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid progress value.");
                sc.nextLine();
                continue;
            }

            int progress = sc.nextInt();
            sc.nextLine();

            if (progress >= 0 && progress <= 100) {
                return progress;
            }

            System.out.println("Progress must be between 0 and 100.");
        }
    }

    private static String handleOptionalCourseCode(Scanner sc, Planner planner) {
        boolean addCourseCode = askYesNo(sc, "Do you want to add course code? (yes/no): ");

        if (!addCourseCode) {
            return "";
        }

        ArrayList<String> savedCodes = planner.getSavedCourseCodes();

        if (savedCodes.isEmpty()) {
            System.out.print("No saved subjects yet. Enter new course code: ");
            return sc.nextLine().trim();
        }

        boolean isNewSubject = askYesNo(sc, "Is this a new subject? (yes/no): ");

        if (isNewSubject) {
            System.out.print("Enter new course code: ");
            return sc.nextLine().trim();
        }

        while (true) {
            System.out.println("Choose subject:");
            for (int i = 0; i < savedCodes.size(); i++) {
                System.out.println((i + 1) + ". " + savedCodes.get(i));
            }

            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid choice.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice >= 1 && choice <= savedCodes.size()) {
                return savedCodes.get(choice - 1);
            }

            System.out.println("Choice out of range.");
        }
    }
}