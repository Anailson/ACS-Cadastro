<?php
include_once "controllers/AgentsController.php";
$controller = new AgentsController();

$result = 1;
$numSus = -1;
$agent = null;
if (isset($_GET['p']) && !empty($_GET['p'])) {

    $result = $controller->delete($_GET['p']);
}
?>
<script src="assets/js/script-general.js" type="text/javascript"></script>
<script src="assets/js/script-agent.js" type="text/javascript"></script>
<script>
    $(function () {
        const result = "<?php echo $result ?>";
        confirmDelete(result);
    })
</script>