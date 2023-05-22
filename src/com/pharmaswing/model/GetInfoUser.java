package com.pharmaswing.model;

public class GetInfoUser {
	  private static GetInfoUser instance = null;
	    private String staffname;
	    private String position;
	    private int idstaff;
	    private int id_position;

	    
	    public int getId_position() {
			return id_position;
		}

		public void setId_position(int id_position) {
			this.id_position = id_position;
		}

		private GetInfoUser() {}
	    
	    public int getIdstaff() {
			return idstaff;
		}

		public void setIdstaff(int idstaff) {
			this.idstaff = idstaff;
		}

	    public static GetInfoUser getInstance() {
	        if (instance == null) {
	            instance = new GetInfoUser();
	        }
	        return instance;
	    }

	    public String getstaffname() {
	        return staffname;
	    }

	    public void setstaffname(String staffname) {
	        this.staffname = staffname;
	    }

	    public String getPosition() {
	        return position;
	    }

	    public void setPosition(String position) {
	        this.position = position;
	    }
	}

