<?php

class Router
{
    const ADD = "novo";
    const EDIT = "editar";
    const DETAIL = "detalhes";
    const DELETE = "deletar";
    const CRUD_ACTIONS = array(self::ADD, self::DETAIL, self::EDIT, self::DELETE);
    const RESTRICTED_PAGES = array("AgentView", "AgentNewView", "AgentDetailsView", "AgentEditView", "AgentDeleteView",
        "ReportView");

    private $routes, $views, $actions;

    function __construct()
    {
        $this->routes = array();
        $this->views = array();
        $this->actions = array();

        $this->setRouters("index", "HomeView");
        $this->setRouters("home", "HomeView");
        $this->setRouters("agentes", "AgentView");
        $this->setRouters("novo-agente", "AgentNewView");
        $this->setRouters("detalhes-agente", "AgentDetailsView");
        $this->setRouters("alterar-agente", "AgentEditView");
        $this->setRouters("remover-agente", "AgentDeleteView");
        $this->setRouters("cidadaos", "CitizenView");
        $this->setRouters("novo-cidadao", "CitizenNewView");
        $this->setRouters("residencias", "ResidenceView");
        $this->setRouters("nova-residencia", "ResidenceNewView");
        $this->setRouters("acompanhamentos", "AccompanyView");
        $this->setRouters("novo-acompanhamento", "AccompanyNewView");
        $this->setRouters("visitas", "VisitView");
        $this->setRouters("nova-visita", "VisitNewView");
        $this->setRouters("mapa-ocorrencias", "OccurrencesMapView");
        $this->setRouters("relatorios", "ReportView");
        $this->setRouters("meus-dados", "MyProfileView");
        $this->setRouters("login", "LoginView");
        $this->setRouters("logout", "LogoutView");

        $this->setCrudActions("agentes", "AgentNewView", "EDIT", "DETAIL", "DELETE");
        $this->setCrudActions("cidadaos", "CIT_NOVO", "CIT_EDIT", "CIT_DETAIL", "CIT_DELETE");
    }

    public function route()
    {
        $size = intval(sizeof($_GET));
        if ($size === 0) {
            header("Location: index");

        } elseif ($size === 1) {

            $route = $_GET['section'];
            $key = array_search($route, $this->routes);

            $page = $key ? $this->views[$key] : "PageNotFoundView";
            $this->verifyAccess($page);

        } elseif ($size === 2) {

            $route = $_GET['section'];
            $action = $_GET['p'];

            $key = array_search($route, $this->routes);

            if ($action === "") {
                $this->includeViews($this->views[$key]);
            } else {
                $page = $key ? $this->views[$key] : "PageNotFoundView";
                $this->verifyAccess($page);
            }

        } elseif ($size === 3) {
            $route = $_GET['section'];
            $action = $_GET['action'];
            $id = $_GET['id'];

            var_dump($_GET);
        }
    }

    private function verifyAccess($page)
    {
        if ($page === "PageNotFoundView") {
            $this->includeViews($page);
        } else {

            $restrict = in_array($page, self::RESTRICTED_PAGES);

            if ($_SESSION['ACCESS'] == 0) {
                $this->includeViews($page);
            } else {
                if($restrict){
                    $this->includeViews("RestrictedPageView");
                } else {
                    $this->includeViews($page);
                }
            }
        }
    }

    private function includeViews($param)
    {

        if ($param != "LoginView" || $param != "LogoutView") {
            include_once "template.php";
        }
        $page = "views/$param.php";
        include_once $page . "";

    }

    private function setRouters($route, $view)
    {
        $this->routes[] = $route;
        $this->views[] = $view;
        $this->actions[$route] = array();
    }

    private function setCrudActions($route, $add, $detail, $edit, $delete)
    {
        $array = array();
        if ($add != "" || $add |= null) {
            $array[] = $add;
        }
        if ($detail != "" || $detail |= null) {
            $array[] = $detail;
        }
        if ($edit != "" || $edit |= null) {
            $array[] = $edit;
        }
        if ($delete != "" || $delete |= null) {
            $array[] = $delete;
        }
        $this->actions[$route] = $array;
    }
}