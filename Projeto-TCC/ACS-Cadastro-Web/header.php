<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar bar1"></span>
                <span class="icon-bar bar2"></span>
                <span class="icon-bar bar3"></span>
            </button>
            <a id="title_menu" class="navbar-brand"></a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-user"></i> <?php echo isset($_SESSION['NAME']) ? $_SESSION['NAME'] : "Usuário"?> <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li id="profile"><a href="meus-dados"><i class="fa fa-info" ></i> Meus Dados</a></li>
                        <li class="divider"></li>
                        <li><a href="logout"><i class="fa fa-power-off"></i> Sair do sistema</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

