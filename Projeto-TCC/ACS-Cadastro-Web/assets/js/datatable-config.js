
$(function () {
    $('.dataTable').DataTable({
        order: [],
        columnDefs: [
            //Disables a sort order of the last three columns (expected: details, edit, delete);
            {orderable: false, targets: -1},
            {orderable: false, targets: -2},
            {orderable: false, targets: -3}
        ],

        language: {
            lengthMenu: "<strong>Exibir&emsp;_MENU_&emsp;registros por página</strong>",
            "zeroRecords": "Nenhum registro encontrado para os parametro informado",
            "info": "Exibindo _PAGE_ de _PAGES_ páginas",
            "infoEmpty": "Nenhum registro encontrado",
            "infoFiltered": "num total de _MAX_ registros",
            "search": "<strong>Pesquisar <por></por>&emsp;_INPUT_</strong>",
            "paginate": {
                "previous": "<strong>Anterior</strong>",
                "next": "<strong>Próxima</strong>"
            }
        }
    });
});