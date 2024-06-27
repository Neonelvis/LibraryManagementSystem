public class Book {
    public class Book {

        private String title;

        private String author;

        private boolean available;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.available = true;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }
}
