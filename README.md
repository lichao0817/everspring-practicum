# Everspring Practicum

## System Requirements
1. Java 8
2. MongoDB
  * New tables `course` and `user` will be created, make sure you have a new database
3. Port 8080

## How to Build and Run the App
1. Clone the project to your computer
```
git clone https://github.com/lichao0817/everspring-practicum.git
cd everspring-practicum
```

2. Build the app with docker:

  ```
  docker build -t app/main .
  docker run -p 8080:8080 -d app/main
  ```
  or you can run the application using go instead:
  ```
  go run main.go
  ```
## Assumptions
1. Different users with same names should be allowed to register.
2. Each class only has one section.
3. To make the url thorter, assume that this is the only version of the API. So no version number is needed in the API url.

## API Services
### 1. Users
#### a) List All Users
##### i) Request
###### URL
```
GET http://localhost:8080/users
```
##### ii) Response
###### Status
`200`: the user list is successfully returned

`204`: there are no users currently
###### Body
``` json
{
    "data": {
        "users": [
            {
                "courses": [],
                "username": "jsmith",
                "name": "Jon Smith",
                "type": "student"
            },
            {
                "courses": [],
                "username": "asmith",
                "name": "Adam Smith",
                "type": "instructor"
            }
        ]
    }
}
```
`courses`: the course codes of the courses that the users enrolled

`username`: the id `string` of the user, case sensitive and contains no space

`name`: the name `string` of the user, case sensitive

`type`: indicate the user type, currently only support "instructor" and "student", case insensitive
#### b) Create a New User
##### i) Request
###### URL
```
POST http://localhost:8080/users
```
###### Body
``` json
{
   "name":"John Doe",
   "username":"jdoe",
   "type":"instructor"
}
```
##### ii) Response
###### Status
`201`: the user has been successfully created.

`400`: either the username or the type is not valid

`409`: an user with the same `username` has already been created
#### c) Find an User by Username
##### i) Request
###### URL
```
GET http://localhost:8080/users/{username}
```
##### ii) Response
###### Status
`200`: the user can be successfully found

`404`: the user cannot be found
###### Body
``` json
{
    "data": {
        "user": {
            "courses": [],
            "username": "jsmith",
            "name": "Jon Smith",
            "type": "student"
        }
    }
}
```
#### d) Update an User by Username
##### i) Request
###### URL
```
PUT http://localhost:8080/users/{username}
```
###### Body
``` json
{
   "name":"John Smith",
   "username":"jsmith",
   "type":"student"
}
```
##### ii) Response
###### Status
`204`: the user has been successfully updated

`404`: the user cannot be found
#### e) Delete an User by Username
##### i) Request
###### URL
```
DELETE http://localhost:8080/users/{username}
```
##### ii) Response
###### Status
`204`: the user can be successfully found

`404`: the user cannot be found
### 2. Classes
#### a) List All Classes
##### i) Request
###### URL
```
GET http://localhost:8080/classes
```
##### ii) Response
###### Status
`200`: the class list is successfully returned

`204`: there are no classes currently
###### Body
``` json
{
    "data": {
        "courses": [
            {
                "users": [],
                "courseCode": "math340",
                "name": "Linear Algebra"
            },
            {
                "users": [],
                "courseCode": "cs302",
                "name": "Intro to Computer Science"
            }
        ]
    }
}
```
`users`: the `list` of users that have been added to the course

`name`: the name `string` of the course

`courseCode`: the id `string` of the course, is case insensitive and cannot be changed once created


#### b) Create a New Class
##### i) Request
###### URL
```
POST http://localhost:8080/classes
```
###### Body
``` json
{
   "name":"Intro to CS",
   "courseCode":"cs302"
}
```

##### ii) Response
###### Status
`201`: the class has been successfully created.

`409`: a class with the same `courseCode` has already been created
#### c) Find a Class by CourseCode
##### i) Request
###### URL
```
GET http://localhost:8080/classes/{courseCode}
```
##### ii) Response
###### Status
`200`: the class can be successfully found

`404`: the class cannot be found
###### Body
``` json
{
    "data": {
        "course": {
            "users": [],
            "courseCode": "math340",
            "name": "Linear Algebra"
        }
    }
}
```
#### d) Update a Class by CourseCode
##### i) Request
###### URL
```
PUT http://localhost:8080/classes/{courseCode}
```
###### Body
``` json
{
   "name":"Intro to CS",
   "courseCode":"cs302"
}
```
##### ii) Response
###### Status
`204`: the class has been successfully updated

`404`: the class cannot be found
#### e) Delete a Class by CourseCode
##### i) Request
###### URL
```
DELETE http://localhost:8080/classes/{courseCode}
```
##### ii) Response
###### Status
`204`: the class can be successfully found

`404`: the class cannot be found
### 3. Enrollment
#### a) Enroll an User to a Class
##### i) Request
###### URL
```
PUT http://localhost:8080/classes/{courseCode}/users/{username}
```
##### ii) Response
###### Status
`201`: the class has been successfully enrolled

`208`: the user has already enrolled

`400`: either the user or class does not exist
#### b) Unroll an User from a Class
##### i) Request
###### URL
```
DELETE http://localhost:8080/classes/{courseCode}/users/{username}
```
##### ii) Response
###### Status
`204`: the class has been successfully unenrolled

`400`: either the user or class does not exist

`404`: the user is not in the class roster
