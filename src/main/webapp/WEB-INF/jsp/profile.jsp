<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mashape.unirest.http.JsonNode" %>
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
                    <a href="/dashboard">
                        <li class="list-item">Dashboard</li>
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
                       JsonNode userInfo = (JsonNode) request.getAttribute("userInfo");
                    %>
                    <div class="img">
                        <% if(userInfo.getObject().get("picture") != null) { %>
                        <img src="<%= userInfo.getObject().get("picture") %>" alt="image" width="200px" height="150px">
                        <% } else { %>
                        <img src="/assets/images/user.png" alt="image" width="200px" height="150px">
                        <% } %>
                    </div>

                    <div class="info">
                        <h2><%= userInfo.getObject().get("email") %></h2>
                        <h3><%= userInfo.getObject().get("nickname") %></h3>
                        <h3><%= userInfo.getObject().get("nickname") %></h3>
                        <h3><%= userInfo.getObject().get("nickname") %></h3>
                        <h3><%= userInfo.getObject().get("nickname") %></h3>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <footer>
        <div class="content">
            2023 Copyright
        </div>
    </footer>

    <script src="/assets/js/main.js"></script>
</body>

</html>