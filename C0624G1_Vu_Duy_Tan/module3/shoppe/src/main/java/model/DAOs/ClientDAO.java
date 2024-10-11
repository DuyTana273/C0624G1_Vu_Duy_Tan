package model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.ConnectionSQL;
import model.entities.Client;

public class ClientDAO {
	public static void insert(String user, String password, String money, String fullname, String birthday,
			String address, String phone) {
		try {
			Connection connection = ConnectionSQL.getConnection();
			PreparedStatement stm = connection.prepareStatement(
					"Insert Into client (user,pass,money,fullname,birthday,address,phone) values(?,?,?,?,?,?,?)");
			stm.setString(1, user);
			stm.setString(2, password);
			stm.setString(3, money);
			stm.setString(4, fullname);
			stm.setString(5, birthday);
			stm.setString(6, address);
			stm.setString(7, phone);
			stm.executeUpdate();
			stm.close();
			connection.close();
			System.out.println("Add client into database successed!");
		} catch (SQLException e) {
			System.out.println("Add client into database failed!");
			e.printStackTrace();
		}
	}

	public static ArrayList<Client> findAll() {
		try {
			ArrayList<Client> clientList = new ArrayList<Client>();
			Connection connection = ConnectionSQL.getConnection();
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("Select * from client");
			while (rs.next()) {
				clientList.add(new Client(rs.getInt("clientID"), rs.getString("user"), rs.getString("pass"),
						rs.getString("money"), rs.getString("fullname"), rs.getDate("birthday").toString(),
						rs.getString("address"), rs.getString("phone"), rs.getString("image")));
			}
			System.out.println("Get client database successed!");
			connection.close();
			return clientList;
		} catch (SQLException e) {
			System.out.println("Get client database failed!");
			e.printStackTrace();
		}

		return null;
	}

	public static Client findByClientId(int clientID) {
		try {
			Connection connection = ConnectionSQL.getConnection();
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(String.format("Select * from client where clientID=%d", clientID));
			System.out.println("Get client database successed!");
			if (rs.next()) {
				return new Client(rs.getInt("clientID"), rs.getString("user"), rs.getString("pass"),
						rs.getString("money"), rs.getString("fullname"), rs.getDate("birthday").toString(),
						rs.getString("address"), rs.getString("phone"), rs.getString("image"));
			}
			connection.close();
		} catch (SQLException e) {
			System.out.println("Get client database failed!");
			e.printStackTrace();
		}

		return null;
	}

	public static Client findByUserAndPass(String user, String pass) {
		try {
			Connection connection = ConnectionSQL.getConnection();
			Statement stm = connection.createStatement();
			ResultSet rs = stm
					.executeQuery(String.format("Select * from client where user=\"%s\"AND pass=\"%s\"", user, pass));
			if (rs.next()) {
				return new Client(rs.getInt("clientID"), rs.getString("user"), rs.getString("pass"),
						rs.getString("money"), rs.getString("fullname"), rs.getDate("birthday").toString(),
						rs.getString("address"), rs.getString("phone"), rs.getString("image"));
			}
			System.out.println("Get client database successed!");
			connection.close();
		} catch (SQLException e) {
			System.out.println("Get client database failed!");
			e.printStackTrace();
		}
		return null;
	}

	public static void updateByClientId(String user, String pass, String fullname, String birthday, String address,
			String phone, String image, int clientId) {
		try {
			Connection connection = ConnectionSQL.getConnection();
			Statement stm = connection.createStatement();
			stm.executeUpdate(String.format(
					"UPDATE client SET user=\"%s\", pass=\"%s\", fullname=\"%s\", birthday=\"%s\", address=\"%s\", phone=\"%s\", image=\"%s\" WHERE clientID=%d;",
					user, pass, fullname, birthday, address, phone, image, clientId));
			connection.close();
			System.out.println("Update account in database successed!");
		} catch (SQLException e) {
			System.out.println("Update account in database failed!");
			e.printStackTrace();
		}
	}

	public static void decreaseMoneyByClientId(long money, int clientID) {
		try {
			Connection connection = ConnectionSQL.getConnection();
			Statement stm = connection.createStatement();
			stm.executeUpdate(String.format("Update client set money=money-%d WHERE clientID=%d", money, clientID));

			connection.close();
			System.out.println("Delete cart in database successed!");
		} catch (SQLException e) {
			System.out.println("Delete cart in database failed!");
			e.printStackTrace();
		}

	}
}
