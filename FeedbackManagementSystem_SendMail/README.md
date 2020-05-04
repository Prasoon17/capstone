# Feedback Management System SendMail application

This application is used to Send Mails to Participants and used to get and set the Events Details 
Implemented Spring Security Using JWT token with Role Based Authentication and Authorization

# Login As Admin

### Request

'POST /login/'
	
	http://localhost:8081/login
	
### Request Body
	
	{
    "username": "admin",
    "password": "admin"
	}
	
### Response 

	{
    	"token": "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoiYWRtaW4iLCJpYXQiOjE1ODczNDMxOTcsImV4cCI6MTU4NzM3MTk5N30.k3E72GLsWfYxvuKf4-hGiBWD7VfdvGiJTLhfB8nO-5KWAe4Yp_PTSEcZs9D84FoM16ZvLyeIHgoUXkToJv37gw"
	} 
	
================================ Like Wise Based on Role login and acess the Rest API PMO and POC ========================================

# REST API

The REST APIs to Feedback Management System SendMail app is described below.

## Get Events From DataBase

### Request

`GET /events/getEvents/`

	 http://localhost:8081/events/getEvents/
	 
	 Authentication = Bearar Token
	 
	 Bearar eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoiYWRtaW4iLCJpYXQiOjE1ODczNDMxOTcsImV4cCI6MTU4NzM3MTk5N30.k3E72GLsWfYxvuKf4-hGiBWD7VfdvGiJTLhfB8nO-5KWAe4Yp_PTSEcZs9D84FoM16ZvLyeIHgoUXkToJv37gw

### Response 

	[
	    {
	        "id": 1,
	        "event_id": "DAE6601S",
	        "base_location": "hyderabad",
	        "beneficiary_name": "shaw",
	        "council_name": "ww",
	        "event_name": "hthht",
	        "event_description": "hh",
	        "event_date": 1391970600000,
	        "employee_id": "bvfb",
	        "employee_name": "A",
	        "volunteer_hours": 10,
	        "travel_hours": 1,
	        "lives_impacted": 2,
	        "business_unit": "grtg",
	        "event_status": "active",
	        "iiep_category": "fsff"
	    },
	    {
	        "id": 2,
	        "event_id": "JDHsdjhg",
	        "base_location": "ckdjcdkj",
	        "beneficiary_name": "vdvdv",
	        "council_name": "vdvv",
	        "event_name": "jjefefe",
	        "event_description": "efe",
	        "event_date": 1395253800000,
	        "employee_id": "kgjg",
	        "employee_name": "feufe",
	        "volunteer_hours": 88,
	        "travel_hours": 0,
	        "lives_impacted": 5,
	        "business_unit": "fef",
	        "event_status": "inactive",
	        "iiep_category": "hth"
	    },
	    {
	        "id": 3,
	        "event_id": "eeef",
	        "base_location": "cdsd",
	        "beneficiary_name": "vsfvbfb",
	        "council_name": "bdgbdgb",
	        "event_name": "bgdgb",
	        "event_description": "ngfn",
	        "event_date": 1430591400000,
	        "employee_id": "vfsdv",
	        "employee_name": "sfb",
	        "volunteer_hours": 55,
	        "travel_hours": 2,
	        "lives_impacted": 2,
	        "business_unit": "dbfbd",
	        "event_status": "dbd",
	        "iiep_category": "dfbd"
	    }
	]

## Get Events By EventId

### Request

'GET' /events/getEvents/{eventId}

	http://localhost:8081/events/getEvents/DAE6601S
	
### Response

	[
	    {
	        "id": 1,
	        "event_id": "DAE6601S",
	        "base_location": "hyderabad",
	        "beneficiary_name": "shaw",
	        "council_name": "ww",
	        "event_name": "hthht",
	        "event_description": "hh",
	        "event_date": "2014-02-09T18:30:00.000+0000",
	        "employee_id": "bvfb",
	        "employee_name": "A",
	        "volunteer_hours": 10,
	        "travel_hours": 1,
	        "lives_impacted": 2,
	        "business_unit": "grtg",
	        "event_status": "active",
	        "iiep_category": "fsff"
	    }
	]


## Get Events By id

### Request

'GET' /events/getEventsById/{id}

	http://localhost:8081/events/getEventsById/6
	
### Response

	{
	    "id": 6,
	    "event_id": "JDHsdjhg",
	    "base_location": "ckdjcdkj",
	    "beneficiary_name": "vdvdv",
	    "council_name": "vdvv",
	    "event_name": "jjefefe",
	    "event_description": "efe",
	    "event_date": 1395253800000,
	    "employee_id": "kgjg",
	    "employee_name": "feufe",
	    "volunteer_hours": 88,
	    "travel_hours": 0,
	    "lives_impacted": 5,
	    "business_unit": "fef",
	    "event_status": "inactive",
	    "iiep_category": "hth"
	}

	
	
## Get Events By VolunteerHours

### Request

'GET' /events/getEventsByVh/{vh}

	http://localhost:8081/events/getEventsByVh/10
	
### Response

	[
	    {
	        "id": 1,
	        "event_id": "DAE6601S",
	        "base_location": "hyderabad",
	        "beneficiary_name": "shaw",
	        "council_name": "ww",
	        "event_name": "hthht",
	        "event_description": "hh",
	        "event_date": 1391970600000,
	        "employee_id": "bvfb",
	        "employee_name": "A",
	        "volunteer_hours": 10,
	        "travel_hours": 1,
	        "lives_impacted": 2,
	        "business_unit": "grtg",
	        "event_status": "active",
	        "iiep_category": "fsff"
	    },
	    {
	        "id": 5,
	        "event_id": "DAE6601S",
	        "base_location": "hyderabad",
	        "beneficiary_name": "shaw",
	        "council_name": "ww",
	        "event_name": "hthht",
	        "event_description": "hh",
	        "event_date": 1391970600000,
	        "employee_id": "bvfb",
	        "employee_name": "A",
	        "volunteer_hours": 10,
	        "travel_hours": 1,
	        "lives_impacted": 2,
	        "business_unit": "grtg",
	        "event_status": "active",
	        "iiep_category": "fsff"
	    }
	]
	
	
## Send Mails To Participants Need To Login As PMO for sending Mail

# Login As pmo

### Request

'POST /login/'
	
	http://localhost:8081/login
	
### Request Body
	
	{
    "username": "pmo",
    "password": "pmo"
	}
	
### Response 

	{
    	"token": "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfUE1PIl0sInN1YiI6InBtbyIsImlhdCI6MTU4NzM0NTQ1NCwiZXhwIjoxNTg3Mzc0MjU0fQ.LkjJsgs0k7UY3m2Um3QosDcCebR6vdfgjWB2pPu3najXHXRPK9ex5y6PSwaKVuJEJ07K1DQksVCYtcQvp2EF5w"
	}

### Request

'GET' /events//sendEmail

	http://localhost:8081/events/sendEmail
	
### Response

	Report has been sent successfully. Please check your email