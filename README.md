# Everspring Practicum

## System Requirements
1. Java 8
2. MongoDB

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
3. To make the url thorter, assume that this is the only version of the API.

## API Services
### 1. Users
#### a) Create a New User
##### i) URL
```
POST http://localhost:8080/users
```
##### ii) Request Body
``` json
{
   "name":"John Doe",
   "username":"jdoe",
   "type":"1"
}
```
##### iii) Response
### 2. Classes
#### a) Find All Classes
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
    "data": [
        {
            "users": [
                "jdoe"
            ],
            "courseCode": "cs302",
            "name": "Intro to CS"
        },
        {
            "users": [],
            "courseCode": "math340",
            "name": "Linear Algebra"
        }
    ]
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
   "data":{  
      "name":"Linear Algebra",
      "courseCode":"math340"
   }
}
```
#### d) Delete a Class by CourseCode
##### i) Request
###### URL
```
DELETE http://localhost:8080/classes/{courseCode}
```
##### ii) Response
###### Status
`204`: the class can be successfully found

`404`: the class cannot be found
#### e) Update a Class by CourseCode
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

#### f) Enroll an User to a Class
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
#### f) Unroll an User to a Class
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
