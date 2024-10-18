public class Book {
    private String title;
    private String author;
    private boolean isIssued;

    // Constructor
    public Book(String title, String author, boolean isIssued) {
        this.title = title;
        this.author = author;
        this.isIssued = isIssued;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }
    public static class User {
        private int userId;
        private String name;

        public User(int userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        public int getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }
    }

}
