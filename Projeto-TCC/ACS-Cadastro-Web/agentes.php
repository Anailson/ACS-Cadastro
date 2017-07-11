<?php include "container_menus.php" ?>
<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_agents").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").text("Agentes de Saúde");
        $("#sub-title").text("Agentes de Saúde").attr("href", "agentes.php");
    });
</script>

<div id="content" class="card">

    <div class="header">
        <h4 class="title">Striped Table with Hover</h4>
        <p class="category">Here is a subtitle for this table</p>
    </div>

    <div class="content table-responsive table-full-width">
        <table class="table table-hover">
            <thead>
            <th class="tdPers col-md-1">Nº do SUS</th>
            <th class="tdPers col-md-1">Área</th>
            <th class="tdPers col-md-1">Equipe</th>
            <th class="tdPers col-md-5">Nome</th>
            <th class="tdPers col-md-1"></th>
            <th class="tdPers col-md-1"></th>
            <th class="tdPers col-md-1"></th>
            </thead>
            <tbody>
            <tr>
                <td>34986095486</td>
                <td>132</td>
                <td>13</td>
                <td>Dakota Rice</td>
                <td>
                    <button type="button" class="btn btn-success btn-fill"><i class="ti-zoom-in"></i>&nbsp;Detalhes
                    </button>
                <td>
                    <button type="button" class="btn btn-primary btn-fill"><i class="ti-pencil-alt"></i>&nbsp;Editar
                    </button>
                </td>
                <td>
                    <button type="button" class="btn btn-danger btn-fill"><i class="ti-trash"></i>&nbsp;Remover</button>
                </td>
            </tr>
            <tr>
                <td>245645654645</td>
                <td>452</td>
                <td>45</td>
                <td>Minerva Hooper</td>
                <td>
                    <button type="button" class="btn btn-success btn-fill"><i class="ti-zoom-in"></i>&nbsp;Detalhes
                    </button>
                <td>
                    <button type="button" class="btn btn-primary btn-fill"><i class="ti-pencil-alt"></i>&nbsp;Editar
                    </button>
                </td>
                <td>
                    <button type="button" class="btn btn-danger btn-fill"><i class="ti-trash"></i>&nbsp;Remover</button>
                </td>
            </tr>
            <tr>
                <td>6675678775675</td>
                <td>564</td>
                <td>56</td>
                <td>Sage Rodriguez</td>
                <td>
                    <button type="button" class="btn btn-success btn-fill"><i class="ti-zoom-in"></i>&nbsp;Detalhes
                    </button>
                <td>
                    <button type="button" class="btn btn-primary btn-fill"><i class="ti-pencil-alt"></i>&nbsp;Editar
                    </button>
                </td>
                <td>
                    <button type="button" class="btn btn-danger btn-fill"><i class="ti-trash"></i>&nbsp;Remover</button>
                </td>
            </tr>
            </tbody>
        </table>

    </div>
</div>