# Everspring Practicum

## System Requirements
1. Java 8
2. MongoDB

## How to Build and Run the App
1. Clone the project to your computer
```
git clone https://github.com/lichao0817/go_assignment.git
cd go_assignment
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
#### a) Create a New Class
##### i) Request
###### URL
```
POST http://localhost:8080/classes
```
###### Body
``` json
{
   "name":"Linear Algebra",
   "courseCode":"18060"
}
```
`name`: the name of the course, should be `string`

`courseCode`: the id of the course, should be `string`

##### ii) Response
###### Status
`201`: the class has been successfully created.

`409`: a class with the same `courseCode` has already been created
#### b) Find a Class by CourseCode
##### i) Request
###### URL
```
GET http://localhost:8080/classes/{courseCode}
```
##### ii) Response
###### Status
`200`: the class can be successfully found

`204`: the class cannot be found
###### Body
``` json
{  
   "data":{  
      "name":"Linear Algebra",
      "courseCode":"math340"
   }
}
```
