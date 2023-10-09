<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/style.css">
    <title>Reservation</title>
</head>

<body>
    <nav>
        <div class="container">
            <div class="brand-logo">
                VRS
            </div>

            <div class="nav-items">
                <div id="profile-icon">
                    <img src="/assets/images/user.png" alt="user-icon" width="20px" height="20px">
                </div>
            </div>
        </div>

        <div id="nav-items-list">
            <ul>
                <li><a href="/dashboard">Dashboard</a></li>
                <li><a href="/profile">Profile</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>
        </div>
    </nav>

    <section id="user-reservation">
        <div class="container">
            <div class="service-form">
                <form method="post" action="/reservation">
                    <label for="date">Date</label>
                    <input type="date" name="date"/>
                    <label for="pretime">Preferred time</label>
                    <select name="pretime" id="pretime">
                        <option value="10am">10 AM</option>
                        <option value="11am">11 AM</option>
                        <option value="12pm">12 PM</option>
                    </select>
                    <label for="prelocation">Preferred Location</label>
                    <input type="text" name="location"/>
                    <label for="vehregnum">Vehicle Registration Number</label>
                    <input type="text" name="vehicleno"/>
                    <label for="currmileage">Current Mileage</label>
                    <input type="text" name="mileage"/>
                    <label for="message">Message</label>
                    <input type="text" name="message"/>
                    <button type="submit">Reserve</button>
                    <button type="reset">Clear</button>
                </form>
                <%
                if(request.getAttribute("response") != null){ %>
                    <p><% request.getAttribute("response"); %></p>
                <% } %>
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