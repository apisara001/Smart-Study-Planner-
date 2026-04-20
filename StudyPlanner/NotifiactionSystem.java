package StudyPlanner;

class NotificationSystem implements Observer {
    public void update(String message) {
        System.out.println("🔔 " + message);
    }
}