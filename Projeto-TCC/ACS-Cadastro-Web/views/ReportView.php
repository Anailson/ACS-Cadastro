<?php  ?>

<script type="text/javascript" src="assets/js/script-report.js"></script>


<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_graphs").addClass("active");
        $("#main_content").html($("#content"));
        //$("#title_menu").html("<i class='fa fa-area-chart'></i>&emsp;Relatórios");
        $("#submenu").append("<li>Relatórios</li>");

        demo.initChartist();
    });
</script>

<div id="content" class="">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Cadastros realizados</h4>
                        <!--<p class="category">Last Campaign Performance</p>-->
                    </div>
                    <div class="content">
                        <div id="chartPreferences" class="ct-chart"></div>

                        <div class="footer">
                            <div class="legend">
                                <i class="fa fa-circle text-primary"></i> Agentes<br/>
                                <i class="fa fa-circle text-info"></i> Cidadãos<br/>
                                <i class="fa fa-circle text-danger"></i> Residências<br/>
                                <i class="fa fa-circle text-warning"></i> Acompanhamentos<br/>
                                <i class="fa fa-circle text-success"></i> Visitas<br/>
                            </div>
                            <!--<hr><div class="stats"><i class="fa fa-clock-o"></i> Campaign sent 2 days ago</div>-->
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Users Behavior</h4>
                        <p class="category">24 Hours performance</p>
                    </div>
                    <div class="content">
                        <div id="chartHours" class="ct-chart"></div>
                        <div class="footer">
                            <div class="legend">
                                <i class="fa fa-circle text-info"></i> Open
                                <i class="fa fa-circle text-danger"></i> Click
                                <i class="fa fa-circle text-warning"></i> Click Second Time
                            </div>
                            <hr>
                            <div class="stats">
                                <i class="fa fa-history"></i> Updated 3 minutes ago
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--   Core JS Files   -->
<script src="assets/js/demo.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    });
</script>
