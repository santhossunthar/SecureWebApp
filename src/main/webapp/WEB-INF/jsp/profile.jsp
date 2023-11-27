<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/style.css">
    <title>Profile</title>
</head>

<body>
    <nav>
        <div class="container">
            <div class="brand-logo">
                VRS
            </div>

            <div class="nav-items">
                <ul class="nav-items-list">
                    <a href="/reservation">
                        <li class="list-item">Reservation</li>
                    </a>
                    <a href="/profile">
                        <li class="list-item">Profile</li>
                    </a>
                    <a href="/logout">
                        <li class="list-item">Logout</li>
                    </a>
                </ul>
            </div>
        </div>
    </nav>

    <section id="user-profile">
        <div class="container">
            <div class="profile-info">
                <div class="header">
                    <%
                       HashMap<String, Object> userInfo = (HashMap<String, Object>) request.getAttribute("userInfo");
                    %>
                    <div class="img">
                        <% if(userInfo.get("picture") != null) { %>
                        <img src="<%= userInfo.get("picture") %>" alt="image" width="200px" height="150px">
                        <% } else { %>
                        <img src="/assets/images/user.png" alt="image" width="200px" height="150px">
                        <% } %>
                    </div>

                     <div class="email-info">
                         <h2><%= userInfo.get("email") %></h2>
                         <h4>
                            <% if((boolean) userInfo.get("emailVerification")) { %>
                            <h4>Email is verified!</h4>
                            <% } else { %>
                            <h4>Email is not verified!</h4>
                            <% } %>
                         </h4>
                     </div>
                </div>

                <div class="content">
                    <div class="element title">Full Name:</div>
                    <div class="element"><%= userInfo.get("fullName").toString() %></div>
                    <div class="element title">Name:</div>
                    <div class="element"><%= userInfo.get("name").toString() %></div>
                </div>
            </div>
        </div>
    </section>

    <footer>
        <div class="content">
            2023 Copyright
        </div>
    </footer>

</body>

</html>