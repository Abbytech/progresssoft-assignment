package abbytech.assignment;
import java.sql.*;

public class Warehouse {

    private  Connection sqlConnection;

    public Warehouse(String connectionUrl,String username,String password) {
        while (sqlConnection==null) {
            try {
                sqlConnection = DriverManager.getConnection(connectionUrl,username,password);
            } catch (SQLException e) {
                String error = String.format("Couldn't connect to sql database server, reason:%s, Exiting..",e.getMessage());
                System.err.println(error);
            }
            System.out.println("Trying again in 3 seconds");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {

            }
        }

        try {
            createTable();
        } catch (SQLException e) {
            String error = String.format("Couldn't create table, reason:%s, Exiting..",e.getMessage());
            System.err.println(error);
            System.exit(1);
        }
        System.out.println("Connected to database");
    }

    public static void main(String[] args) {
        if (args.length!=3){
            System.out.println("Usage: <connection string> <username> <password>");
            return;
        }

        String connectionUrl = args[0];
        String username = args[1];
        String password = args[2];
        Warehouse warehouse = new Warehouse(connectionUrl,username,password);


        long id = Math.round(Math.random() * 10000);
        float amount = (float) (Math.random()*100);
        Deal deal = new Deal(id, "JOD",
                "JOD",
                System.currentTimeMillis()/1000, amount);

        try {
            warehouse.acceptDeal(deal);
        } catch (SQLException e) {
            System.err.println(String.format("Failed to accept sample deal, reason:%s",e.getMessage()));
        }
    }


    public  void acceptDeal(Deal deal) throws SQLException {
        String insertSql =
                "INSERT INTO deal(`id`,`ordering_currency_iso_code`,`to_currency_iso_code`,`amount`,`timestamp`)" +
                        " VALUES('%d','%s','%s','%f','%s')";
        String sql = String.format(insertSql,deal.getId(),
                deal.getOrderingCurrencyISOCode(),
                deal.getToCurrencyISOCode(),
                deal.getAmount(),
                deal.getTimestamp().toString()
        );

        PreparedStatement preparedStatement = sqlConnection.prepareStatement(sql);
        preparedStatement.execute();

        System.out.println(String.format("Accepted deal with id: %d",deal.getId()));
    }

    private void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS `deal`(" +
                "`id` int(11) NOT NULL," +
                "`ordering_currency_iso_code` varchar(3) NOT NULL," +
                "`to_currency_iso_code` varchar(3) NOT NULL," +
                "`amount` float NOT NULL," +
                "`timestamp` timestamp," +
                "PRIMARY KEY (`id`));";
        PreparedStatement preparedStatement = sqlConnection.prepareStatement(sql);
        preparedStatement.execute();

        System.out.println("'deal' table created");
    }
}
