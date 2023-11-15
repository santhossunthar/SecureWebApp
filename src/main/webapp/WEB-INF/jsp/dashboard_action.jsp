<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/style.css">
    <title>Dashboard</title>
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

    <section id="msg-container">
        <div class="container">
            <div class="msg-body">
                <div class="title">
                    <h1>
                        <%
                        String msg = (String) request.getAttribute("msg");
                        if(msg.equals("empty")) { %>
                        You have not made any reservation yet!
                        <% } else { %>
                        Something went wrong, try again!
                        <% } %>
                    </h1>
                </div>

                <div class="btn-group">
                    <a href="/reservation"><button class="btn">Reserve</button></a>
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