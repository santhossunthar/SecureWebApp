<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    <section id="user-profile">
        <div class="container">
            <div class="profile-info">
                <div class="header">
                    <div class="img">
                        <img src="/assets/images/user.png" alt="image" width="200px" height="150px">
                    </div>

                    <div class="info">
                        <h2>email@email.com</h2>
                        <h3>Username</h3>
                        <h3>name</h3>
                        <h3>Contact number</h3>
                        <h3>Country</h3>
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