# MicroService: Resturcturing my Job Application from Monolithic architecture to MicroService.

![MicroService](image.png)

## ``` How I am going to structure my three microservice ```
![Port](image-1.png)

    I have 3 different microservice running on 3 different ports, We can see in the above image.

    These 3 microservices are going to 3 different springboot projects.

``` Why so? ```

    we are going to break down a single application which is in
    monolithic architecture into 3 different parts and they will
    be responsible for running as a single application. So for 
    the end user it will be like they are interacting a single
    application but in backend I have 3 different microservices
    running on 3 different ports

### ``` New review urls for microservice ```

![review urls](image-2.png)

    In my monolithic architecture my review urls is:- 
    
    [ GET /companies/{companyId}/reviews ] --> this url use path parameter
    mean's within the url path I had the companyId parameter.

    But in my microservice review urls are different:-

    Here I used Query parameter mean's passing the parameter in the form of
    query.
``` Query Parameter: It is a optional parameters in a URL that modify the behaviour of request ```  

    In new urls we can see I used [ GET /reviews?companyId={companyId} ]
    here review microservice companyId not a part of this ms that's
    why I accepted it as query parameter.

``` Path Parameter: When I pass a parameter in the path itself ```



    The reason behind resturcturing the urls is my microservices less to know about each other, need to be independent.

    
# ``` Inter-service communication: ```
    Here I have 3 microservices running on diffierent ports, use different database but for user or client these services should be running as a single unit.
    There is a need for these services to communicate with each others so they can
    function as a single unit. This is where we need [Inter-Service Communication].

``` What is Inter-Service Communication:- ```

    In simple term, It's a method through which microservices in an application communicate with each other.

    This communication between services can happen Synchronously and Asynchronously
    and we can use protocol like HTTP or Rest messaging