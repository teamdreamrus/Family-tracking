<#macro page pageTitle>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"  integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
</head>
<body>


<header class="header">
    <div class="overlay">
            <nav class="navbar navbar-expand-lg fixed-top">
                <a class="navbar-brand" href="/">Family Tracking</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
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
                        <a class="nav-link " href="/registration">Зарегистрироваться</a>
                        </li>
                        <li class="nav-item">
                        <a class="nav-link " href="/logout">Выйти</a>
                        </li>
                    </ul>
                </div>
            </nav>
    </div>

    <div class="container">
        <div class="row">
            <#nested>
        </div>
    </div>
</header>

<script type="text/javascript">
    $(document).ready(function(){
        $('.header').height($(window).height());
    })
</script>

</html>
</#macro>