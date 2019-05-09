<#macro page pageTitle>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"  integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="form-group">
            <a class="btn-link" href="/login">Login</a>
            <a class="btn-link" href="/logout">Logout</a>
            <a class="btn-link" href="/registration">Register</a>
        </div>
    </div>
    <div class="row">

        <#nested>

    </div>
</div>
</html>
</#macro>