// Project Title: Movie Collection Management System Using BST
// Objective: Create a system to manage a personal movie collection using a Binary Search Tree (BST). Each movie is organized by its unique ID, allowing users to efficiently add, search, and delete movies, and display the collection in sorted order.
// Project Requirements
// The Movie Collection Management System should support the following operations:
// 1.	Add a New Movie:
// o	Insert movies based on:
// *	Movie ID: Unique integer identifier for each movie.
// *	Title: Name of the movie (e.g., "Inception").
// *	Release Year: The year the movie was released (e.g., 2010).
// o	Insert movies into the BST by Movie ID to maintain ordering.
// 2.	Search for a Movie by ID:
// o	Allow the user to search for a movie by Movie ID.
// o	If found, display the movie’s details (Title and Release Year). If not, inform the user that the movie is not in the collection.
// 3.	Delete a Movie by ID:
// o	Remove a movie from the collection by Movie ID.
// o	Handle all three cases of node deletion in a BST (node with no children, one child, and two children).
// 4.	Display All Movies in Ascending Order of ID:
// o	Use an in-order traversal to display all movies, showing them sorted by Movie ID.
// o	Each movie’s ID, Title, and Release Year should be shown.


import java.util.Scanner;

// Define the structure for a movie node
class MovieNode {
    int movieId;
    String title;
    int releaseYear;
    MovieNode left, right;

    public MovieNode(int id, String title, int year) {
        this.movieId = id;
        this.title = title;
        this.releaseYear = year;
        this.left = null;
        this.right = null;
    }
}

public class Movie_Collection {
    private MovieNode root;

    // Function to insert a movie into the BST
    public MovieNode insertMovie(MovieNode root, int id, String title, int year) {
        if (root == null) {
            return new MovieNode(id, title, year);
        }
        if (id < root.movieId) {
            root.left = insertMovie(root.left, id, title, year);
        } else if (id > root.movieId) {
            root.right = insertMovie(root.right, id, title, year);
        } else {
            System.out.println("Movie ID " + id + " already exists.");
        }
        return root;
    }

    // Function to search for a movie by ID
    public void searchMovie(MovieNode root, int id) {
        if (root == null) {
            System.out.println("Movie with ID " + id + " not found.");
            return;
        }
        if (id < root.movieId) {
            searchMovie(root.left, id);
        } else if (id > root.movieId) {
            searchMovie(root.right, id);
        } else {
            System.out.println("Movie Found:");
            System.out.println("ID: " + root.movieId + ", Title: " + root.title + ", Release Year: " + root.releaseYear);
        }
    }

    // Function to find the minimum node in a BST
    public MovieNode findMin(MovieNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Function to delete a movie by ID
    public MovieNode deleteMovie(MovieNode root, int id) {
        if (root == null) {
            System.out.println("Movie with ID " + id + " not found.");
            return root;
        }
        if (id < root.movieId) {
            root.left = deleteMovie(root.left, id);
        } else if (id > root.movieId) {
            root.right = deleteMovie(root.right, id);
        } else {
            // Node with one or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Node with two children
            MovieNode temp = findMin(root.right);
            root.movieId = temp.movieId;
            root.title = temp.title;
            root.releaseYear = temp.releaseYear;
            root.right = deleteMovie(root.right, temp.movieId);
        }
        return root;
    }

    // Function to display all movies using in-order traversal
    public void displayMovies(MovieNode root) {
        if (root != null) {
            displayMovies(root.left);
            System.out.println("ID: " + root.movieId + ", Title: " + root.title + ", Release Year: " + root.releaseYear);
            displayMovies(root.right);
        }
    }

    // Main function
    public static void main(String[] args) {
        Movie_Collection collection = new Movie_Collection();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMovie Collection Management System");
            System.out.println("1. Add Movie");
            System.out.println("2. Search Movie");
            System.out.println("3. Delete Movie");
            System.out.println("4. Display All Movies");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Movie ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Release Year: ");
                    int year = scanner.nextInt();
                    collection.root = collection.insertMovie(collection.root, id, title, year);
                    break;

                case 2:
                    System.out.print("Enter Movie ID to search: ");
                    id = scanner.nextInt();
                    collection.searchMovie(collection.root, id);
                    break;

                case 3:
                    System.out.print("Enter Movie ID to delete: ");
                    id = scanner.nextInt();
                    collection.root = collection.deleteMovie(collection.root, id);
                    break;

                case 4:
                    System.out.println("Movie Collection (Sorted by ID):");
                    collection.displayMovies(collection.root);
                    break;

                case 5:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

