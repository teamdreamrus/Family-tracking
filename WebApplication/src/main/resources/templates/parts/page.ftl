<#macro page pageTitle>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle}</title>
    <link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"  integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/defaultTheme.css" media="screen" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src='/resources/js/jquery.fixedheadertable.js'></script>
</head>
<body>
<header class="header">
<div class="overlay">
<nav class="navbar navbar-expand-lg fixed-top">

    <a class="navbar-brand" href="/"><i class="fas fa-map-marked-alt"></i> Family Tracking</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"><i class="fas fa-bars"></i></span>
    </button>
    <div class="collapse navbar-collapse " id="navbarSupportedContent">
        <ul class="navbar-nav mr-4">
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/profile">Личный кабинет</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/friends">Друзья</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/registration">Регистрация</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/logout">Выход</a>
            </li>
        </ul>
    </div>
</nav>
</div>
</header>
<section id="main">
    <div class="wrapping">
    </div>
    <#nested>
</section>


</body>
</html>
</#macro>