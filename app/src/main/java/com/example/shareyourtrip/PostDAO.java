package com.example.shareyourtrip;

import java.sql.Connection;
/*import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public PostDAO() {

    }

    /*
      @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //<------------------------------------- need to change to our database driver
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
                    .getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?"  //<------------------------------------- need to change to our database connector
                            + "user=john&password=pass1234");
            System.out.println(connect);
        }
    }


    /*************************************************************************************/

    public List<Post> listAllPost(String sql) throws SQLException {

        List<Post> listPost = new ArrayList<Post>();
        connect_func();
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String city = resultSet.getString("city");
            String state = resultSet.getString("state");
            String category = resultSet.getString("category");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");

            Post post = new Post(city, state, category, title, description);
            listPost.add(post);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return listPost;
    }

    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
            connect.close();
        }
    }

    /*************************************************************************************/

    public boolean insert(Post post) throws SQLException {
        connect_func();
        String sql = "insert into  post(city,state,category,title,description) values (?, ?, ?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, post.getCity());
        preparedStatement.setString(2, post.getState());
        preparedStatement.setString(3, post.getCategory());
        preparedStatement.setString(4, post.getTitle());
        preparedStatement.setString(5, post.getDescription());

//		preparedStatement.executeUpdate();

        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowInserted;
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM post WHERE id = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowDeleted;
    }

    /*
     @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }*/

    public Post getPost(String val1, String val2) throws SQLException {
        Post post = null;
        String sql = "SELECT * FROM post WHERE ? = ?";

        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, val1);
        preparedStatement.setString(2, val2);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String city = resultSet.getString("city");
            String state = resultSet.getString("state");
            String category = resultSet.getString("category");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");

            post = new Post(city, state, category, title, description);
        }

        resultSet.close();
        preparedStatement.close();

        return post;
    }

    public void initialize() throws SQLException {

        connect_func();

/*        String sql = "drop database projectdb";

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        sql = "create database projectdb";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
*/
        String sql = "use projectdb"; //<----------------------use database name here
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        sql = "create table post" +
                "(id integer not null auto_increment,"+
                "city varchar(20) NOT NULL,"+
                "state varchar(20) NOT NULL,"+
                "category varchar(20) NOT NULL,"+
                "title varchar(20) NOT NULL,"+
                "description varchar(150),"+
                "r_user varchar(20),"+

                "r_user reference User (username),"+
                "primary key (id))";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();


        Post newPost = new Post("Detroit", "Michigan", "Food", "Urban Ramen" ,"Best ramen place in all of Detroit!");
        insert(newPost);

        //boolean rowUpdated = preparedStatement.executeUpdate() > 0;

    }
}
