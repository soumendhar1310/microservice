package sample;

public class IoTInput {

	 private String temp;

	    private String longitude;

	    private String latitude;

	    private String humid;

	    public String getTemp ()
	    {
	        return temp;
	    }

	    public void setTemp (String temp)
	    {
	        this.temp = temp;
	    }

	    public String getLongitude ()
	    {
	        return longitude;
	    }

	    public void setLongitude (String longitude)
	    {
	        this.longitude = longitude;
	    }

	    public String getLatitude ()
	    {
	        return latitude;
	    }

	    public void setLatitude (String latitude)
	    {
	        this.latitude = latitude;
	    }

	    public String getHumid ()
	    {
	        return humid;
	    }

	    public void setHumid (String humid)
	    {
	        this.humid = humid;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [temp = "+temp+", longitude = "+longitude+", latitude = "+latitude+", humid = "+humid+"]";
	    }
	
}
