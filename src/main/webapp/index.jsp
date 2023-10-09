<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/style.css">
    <title>Login</title>
</head>

<body>
    <header>
        <nav>
            <div class="container">
                <div class="brand-logo">
                    VSR
                </div>

                <div class="nav-items">
                    <div class="profile-icon">
                        <img src="/assets/images/user.png" alt="user-icon" width="20px" height="20px">
                    </div>
                </div>
            </div>
        </nav>
    </header>

    <section id="content">
        <div class="container">
            <div class="login-form">
                <div class="header">
                    Welcome to Vechile Service Reservation!
                </div>

                <div class="body">
                    <a href="https://dev-q4sknn3f2n0c67zb.us.auth0.com/authorize?response_type=code&scope=openid%20profile&client_id=0v0Qb81hBN2hKC4EaOzr11WIq3hUGNB7&redirect_uri=http://localhost:8080/callback&state=some-state&nonce=some-nonce">
                            Login to the System!
                    </a>
                </div>
            </div>
        </div>
    </section>

    <footer>
        <div class="content">
            2023 Copyright | VSR
        </div>
    </footer>
</body>

</html>