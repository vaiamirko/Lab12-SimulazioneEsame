package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.model.Distretto;
import it.polito.tdp.model.Event;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	
	public List<Integer> getAllAnni(){
		String sql = "SELECT YEAR(e.reported_date) AS anno  " + 
				"FROM `events` AS e  " + 
				"GROUP BY anno  " + 
				"" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Integer> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(res.getInt("anno"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
		
		public List<Integer> getAllidDistretti(){
			String sql = "SELECT e.district_id AS id_distretto  " + 
					"FROM `events` AS e   " + 
					"GROUP BY id_distretto";
			try {
				Connection conn = DBConnect.getConnection() ;

				PreparedStatement st = conn.prepareStatement(sql) ;
				
				List<Integer> list = new ArrayList<>() ;
				
				ResultSet res = st.executeQuery() ;
				
				while(res.next()) {
					try {
						list.add(res.getInt("id_distretto"));
					} catch (Throwable t) {
						t.printStackTrace();
						System.out.println(res.getInt("id"));
					}
				}
				
				conn.close();
				return list ;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null ;
			}
			
		
	}
		public List<Distretto> getAllDistretti(int anno){
			String sql = "SELECT e.district_id,AVG(e.geo_lon) AS g_lon , AVG(e.geo_lat) AS g_lat " + 
					"FROM `events` AS e  " + 
					"WHERE YEAR(e.reported_date)= ?  " + 
					"GROUP BY e.district_id";
			try {
				Connection conn = DBConnect.getConnection() ;

				PreparedStatement st = conn.prepareStatement(sql) ;
				st.setInt(1, anno);
				
				List<Distretto> list = new ArrayList<>() ;
				
				ResultSet res = st.executeQuery() ;
				
				while(res.next()) {
					try {
						list.add(new Distretto(res.getInt("e.district_id"), res.getDouble("g_lon"),res.getDouble("g_lat")));
					} catch (Throwable t) {
						t.printStackTrace();
						System.out.println(res.getInt("id"));
					}
				}
				
				conn.close();
				return list ;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null ;
			}
			
		
	}
	
	
	
	

}
