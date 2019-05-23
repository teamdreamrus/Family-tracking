<#import "parts/page.ftl" as pageTemplate>

<@pageTemplate.page "Family Tracking">

    <link rel="stylesheet" type="text/css" href="/resources/css/main_style.css">

    <div id="headerwrap">
        <div class="container">
            <div class="row centered">
                <div class="col-lg-8 col-lg-offset-2">
                    <h1>Не спрашивай, где твой <b>друг</b></h1>
                    <h2>Скажи ему об этом сам!</h2>
                </div>
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- headerwrap -->

    <div class="container w">
        <div class="row centered">
            <div class="col-lg-4">
                <i class="fa fa-key"></i>
                <h4>РЕГИСТРАЦИЯ</h4>
                <p>Зарегистрируйся прямо сейчас, чтобы участвовать в проекте. Это просто ;)</p>
            </div>

            <br><br>
            <div class="col-lg-4">
                <i class="fa fa-user"></i>
                <h4>ДРУЖБА</h4>
                <p>Добавляй в друзей тех, кто для тебя важен.</p>
            </div>
            <!-- col-lg-4 -->

            <div class="col-lg-4">
                <i class="fa fa-mobile"></i>
                <h4>ПРИЛОЖЕНИЕ</h4>
                <p>Чтобы делиться своим местоположением с друзьями, установи мобильное приложение.</p>
            </div>
            <!-- col-lg-4 -->

            <!-- col-lg-4 -->
        </div>
        <!-- row -->
        <br>
        <br>
    </div>
    <!-- container -->

    <!-- PORTFOLIO SECTION -->
    <div id="dg">
        <div class="container">
            <div class="row centered justify-content-center">
                <div class="col">
                    <h4>МОБИЛЬНО. АДАПТИВНО.</h4>
                </div>
            </div>
            <div class="row centered">
                <br>
                <div class="col-lg-4">
                    <div class="tilt">
                        <a href="#"><img src="/resources/images/p01.png" alt=""></a>
                    </div>
                </div>

                <div class="col-lg-4">
                    <div class="tilt">
                        <a href="#"><img src="/resources/images/p02.png" alt=""></a>
                    </div>
                </div>

                <div class="col-lg-4">
                    <div class="tilt">
                        <a href="#"><img src="/resources/images/p03.png" alt=""></a>
                    </div>
                </div>
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- DG -->

    <div id="r">
        <div class="container">
            <div class="row centered">
                <div class="col-lg-8 col-lg-offset-2">
                    <h4>ПРОЕКТ FAMILY TRACKING СОЗДАН ИСКЛЮЧИТЕЛЬНО ДЛЯ УЧЕБНОЙ ПРАКТИКИ И НЕ ПРЕСЛЕДУЕТ ДУРНЫХ ЦЕЛЕЙ ИЛИ ЦЕЛЕЙ ЗАРАБОТКА.</h4>
                    <p>Пользуясь этим сервисом, вы несёте полную ответственность за предоставляемые сервису данные.</p>
                </div>
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>

    <div id="copyrights">
        <div class="container">
            <div class="credits">
                Ознакомиться с подробностями работы сервиса можно на странице <a href="https://www.github.com/teamdreamrus/Family-tracking" style="color: whitesmoke">GitHub</a>
            </div>
        </div>
    </div>



    <script>
        function funonload(){


        }  window.onload = funonload;
    </script>
</@pageTemplate.page>
