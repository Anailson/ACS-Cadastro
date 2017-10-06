var map = {};
var filter = {};
var parameter = {};

const markers = [];
const baseUrl = "marker_details.php?f=map";
const myLocation = {lat: -10.7, lng: -37.45};

function initMap() {
    
    map = new google.maps.Map(document.getElementById('map'), {
        center: myLocation,
        zoom: 11
    });

    function success(position) {
        myLocation.lat = position.coords.latitude;
        myLocation.lng = position.coords.longitude;
        map.setCenter(myLocation);
        map.setZoom(15);
    }

    function error() {
        $.notify({
            message: '<br>Não foi possível definir sua localização.<br><br>'
        },{
            type: 'info',
            timer: 4000,
            placement: {
                from: 'top',
                align: 'right'
            }
        });
    }

    filter = document.getElementById('filter');
    parameter = document.getElementById('parameter');
    navigator.geolocation.getCurrentPosition(success, error);
}

function changeOccurrence() {
    const options = [{"text": "Todos", "value": "ALL"}];

    switch (filter.value) {
        case "SOCIAL_DEMOGRAPHIC":
            alert("SOCIAL_DEMOGRAPHIC");
            break;
        case "HEALTH_CONDITIONS":
            options.push({"text": "Gestantes", "value": "PREGNANT"},
                {"text": "Fumantes", "value": "SMOKER"},
                {"text": "Uso de alcool", "value": "ALCOHOL"},
                {"text": "Uso de outras drogas", "value": "DRUGS"},
                {"text": "Hipertensão arterial", "value": "HYPERTENSION"},
                {"text": "Diabetes", "value": "DIABETES"},
                {"text": "AVC/Derrame", "value": "AVC"},
                {"text": "Infarto", "value": "HEART_ATTACK"},
                {"text": "Doença cardíaca", "value": "HEART_DISEASE"},
                {"text": "Insuficiência cardíaca", "value": "HEART_DISEASE"},
                {"text": "Doença cardíaca (outra / não sabe)", "value": "HEART_DISEASE"},
                {"text": "Doença renal", "value": "KIDNEY_DISEASE"},
                {"text": "Insuficiência renal", "value": "KIDNEY_DISEASE"},
                {"text": "Doença cardíaca (outra / não sabe)", "value": "KIDNEY_DISEASE"},
                {"text": "Doença respiratória", "value": "RESPIRATORY_DISEASE"},
                {"text": "Asma", "value": "RESPIRATORY_DISEASE"},
                {"text": "DPOC/Empisema", "value": "RESPIRATORY_DISEASE"},
                {"text": "Doença respiratória (outra / não sabe)", "value": "RESPIRATORY_DISEASE"},
                {"text": "Tuberculose", "value": "TUBERCULOSIS"},
                {"text": "Cancer", "value": "CANCER"},
                {"text": "Problemas de saúde mental", "value": "MENTAL_HEALTH"},
                {"text": "Acamado", "value": "IN_BED"},
                {"text": "Domiciliado", "value": "DOMICILED"},
                {"text": "Práticas alternativas", "value": "OTHER_PRACTICES"},
                {"text": "Cancer", "value": "CANCER"},
                {"text": "Problemas de saúde mental", "value": "MENTAL_HEALTH"},
                {"text": "Acamado", "value": "IN_BED"},
                {"text": "Domiciliado", "value": "DOMICILED"},
                {"text": "Práticas alternativas", "value": "OTHER_PRACTICES"},
                {"text": "Internação nos ultimos 12 meses", "value": "INTERMENT"},
                {"text": "Uso de plantas medicinais", "value": "PLANT"});
            break;
        case "STREET_SITUATION":
            alert("STREET_SITUATION");
            break;
        case "CONDITIONS":
            alert("CONDITIONS");
            break;
        case "EXAMS":
            alert("EXAMS");
            break;
        case "HOUSING_CONDITIONS":
            alert("HOUSING_CONDITIONS");
            break;
        default:
            alert("default");
            break;
    }
    setOptions(parameter, options);
}

function setOptions(select, options) {
    select.options.length = 0;
    for (var i = 0; i < options.length; i++) {
        select.options[select.options.length] = new Option(options[i].text.toUpperCase(), options[i].value);
    }
    $('#parameter').trigger('chosen:updated');
}

function getMarkers() {

    const l = filter.value == "" || filter.value == "ALL" ? "" : "&l=" + filter.value;
    const p = parameter.value == "" || parameter.value == "ALL" ? "" : "&p=" + parameter.value;
    const url = baseUrl + l + p;

    const ajax = new XMLHttpRequest();

    ajax.open("GET", url, true);
    ajax.onreadystatechange = function () {

        if (ajax.readyState == 4) {

            deleteMarkers();

            const locations = JSON.parse(ajax.responseText);
            for (var i = 0; i < locations.length; i++) {
                addMarker(locations[i]);
            }
            setCenter();
        }
    };
    ajax.send(null);
}

function deleteMarkers(){
    for(var i = 0; i < markers.length; i++){
        markers[i].setMap(null);
    }
    markers.length = 0;
}

function addMarker(location){
    const marker = new google.maps.Marker({
        position: {lat: location.lat, lng: location.lng},
        label: location.count + '',
        map: map
    });
    markers.push(marker);
    addInfoOnMarker(marker, location.details);
}

function addInfoOnMarker(marker, info){

    if(typeof info !== 'undefined'){
        google.maps.event.addListener(marker, 'click', (function (){
            return function () {
                const infoWindow = new google.maps.InfoWindow();
                infoWindow.setContent(info);
                infoWindow.open(map, marker);
            }
        })());
    }
}

function setCenter(){

    const values = {minorLat: myLocation.lat,
        maxLat: myLocation.lat,
        minorLng: myLocation.lng,
        maxLng: myLocation.lng};

    if(markers.length > 0){
        values.maxLat = values.minorLat = markers[0].position.lat();
        values.maxLng = values.minorLng = markers[0].position.lng();
    }

    for(var i = 1; i < markers.length; i++){
        values.minorLat = (markers[i].position.lat() < values.minorLat) ? markers[i].position.lat() : values.minorLat;
        values.maxLat = (markers[i].position.lat() > values.maxLat) ? markers[i].position.lat() : values.maxLat;
        values.minorLng = (markers[i].position.lng() < values.minorLng) ? markers[i].position.lng() : values.minorLng;
        values.maxLng = (markers[i].position.lng() > values.maxLng) ? markers[i].position.lng() : values.maxLng;
    }
    const center = {
        lat: values.minorLat + (values.maxLat - values.minorLat) / 2,
        lng: values.minorLng + (values.maxLng - values.minorLng) / 2
    };

    const zoom = getZoom(Math.max((values.maxLat - values.minorLat), (values.maxLng - values.minorLng)));
    console.log(zoom);
    map.setCenter(center);
    map.setZoom(zoom);
}

function getZoom(max){
    console.log(max);
    if( max < 0.05){
        return 14;
    } else if(max >= 0.05 && max < 0.1){
        return 13;
    } else if(max >= 0.1 && max < 0.2){
        return 12;
    } else if(max >= 0.2 && max < 0.4){
        return 11;
    } else if(max >= 0.5 && max < 0.8){
        return 10;
    } else if(max >= 0.8 && max < 1.4){
        return 9;
    } else if(max >= 1.4 && max < 2.5){
        return 8;
    } else if(max >= 2.5 && max < 4){
        return 7;
    } else if(max >= 4){
        return 6;
    }
}