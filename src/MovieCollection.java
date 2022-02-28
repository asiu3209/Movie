import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a tital search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast()
    {
        System.out.print("Enter a Cast search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();
        ArrayList<String> casts = new ArrayList<>();

        for (int i = 0; i < movies.size(); i++)
        {
            boolean smth = true;
            String tempKeyword = movies.get(i).getCast();
            String[] tempKeyword2 = tempKeyword.split("\\|");
            for (int p= 0; p < tempKeyword2.length;p++){
                String tempKey3 = tempKeyword2[p].toLowerCase();
                if (tempKey3.contains(searchTerm) && !(casts.contains(tempKeyword2[p]))){
                    casts.add(tempKeyword2[p]);
                    if (smth){
                        results.add(movies.get(i));
                        smth = false;
                    }
                }
            }
        }

        for (int j = 1; j < casts.size(); j++)
        {
            String temp = casts.get(j);

            int possibleIndex = j;
            while (possibleIndex > 0 && temp.compareTo(casts.get(possibleIndex - 1)) < 0)
            {
                casts.set(possibleIndex, casts.get(possibleIndex - 1));
                possibleIndex--;
            }
            casts.set(possibleIndex, temp);
        }

        // now, display them all to the user
        for (int i = 0; i < casts.size(); i++)
        {
            String castss = casts.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + castss);
        }

        System.out.println("Which person would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        String selected = casts.get(choice -1);
        ArrayList<Movie> results2 = new ArrayList<>();
        for (int i = 0; i < results.size();i++){
            if (results.get(i).getCast().contains(selected)){
                results2.add(results.get(i));
            }
        }
        for (int i = 0; i < results2.size(); i++)
        {
            Movie print = results2.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + print.getTitle());
        }

        System.out.println("Which Movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice2 = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie2 = results2.get(choice2 - 1);

        displayMovieInfo(selectedMovie2);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void searchKeywords()
    {
        System.out.print("Enter a keyWord search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String tempKeyword = movies.get(i).getKeywords();
                if (tempKeyword.indexOf(searchTerm) != -1) {
                    results.add(movies.get(i));
                }

        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres()
    {
        ArrayList<String> results = new ArrayList<String>();
        for (int i = 0; i<movies.size();i++){
            String temp = movies.get(i).getGenres();
            String[] temp2 = temp.split("\\|");
            for (int j = 0;j<temp2.length;j++){
                if (!results.contains(temp2[j])){
                    results.add(temp2[j]);
                }
            }
        }
        for (int j = 1; j < results.size(); j++)
        {
            String temp = results.get(j);

            int possibleIndex = j;
            while (possibleIndex > 0 && temp.compareTo(results.get(possibleIndex - 1)) < 0)
            {
                results.set(possibleIndex, results.get(possibleIndex - 1));
                possibleIndex--;
            }
            results.set(possibleIndex, temp);
        }
        for (int i = 0;i<results.size();i++){
            String title = results.get(i);
            int choiceNum = i+1;
            System.out.println("" + choiceNum + ". " + title);
        }
        System.out.println("Which genre would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Movie> results2 = new ArrayList<>();
        String selectedGenre = results.get(choice - 1);
        for (int i = 0; i< movies.size();i++){
            if (movies.get(i).getGenres().contains(selectedGenre)){
                results2.add(movies.get(i));
            }
        }
        for (int i = 0;i<results2.size();i++){
            String title = results2.get(i).getTitle();
            int choiceNum = i+1;
            System.out.println("" + choiceNum + ". " + title);
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie2 = results2.get(choice - 1);

        displayMovieInfo(selectedMovie2);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRated()
    {
        ArrayList<Double> Ratings = new ArrayList<Double>();
        ArrayList<Movie> results = new ArrayList<>();
        ArrayList<Movie> tempMovies = movies;
        int count = 0;
        for (int i = 0; i < movies.size();i++){
            double maxRating = tempMovies.get(i).getUserRating();
            int maxPosition = 0;
            for (int j = i+1;j<movies.size();j++){
                if (tempMovies.get(j).getUserRating() >= maxRating){
                    maxRating = tempMovies.get(j).getUserRating();
                    maxPosition = j;
                }
            }
            Ratings.add(maxRating);
            results.add(tempMovies.get(maxPosition));
            tempMovies.remove(maxPosition);
            i--;
            count++;
            if (count == 50){
                break;
            }
        }

    }

    private void listHighestRevenue()
    {

    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}