var map;
var marker;
var markers = [];
var markersBounds;

var track;
var tracks = [];
var tracksBounds;
function create2GisMap(latitude, longtitude){
    DG.then(function(){
        map = DG.map('map', {center: [latitude, longtitude], zoom: 17});
    });
}
function set2GisMapMarker(latitude, longtitude, tag){
    DG.then(function(){
            if (marker != undefined){
                marker.removeFrom(map);
                marker.setLatLng([latitude, longtitude]);
                marker.addTo(map).bindPopup(tag);
            }
            else{
                marker = DG.marker([latitude, longtitude]);
                marker.addTo(map).bindPopup(tag);
            }
            map.panTo(marker.getLatLng());
        }
    );
}

function add2GisMapMarkers(latitude, longtitude, tag){
    DG.then(function(){
            if (markers === undefined) {
                markers = [];
            }
            var newMarker = DG.marker([latitude, longtitude]);
            markers.push(newMarker);
            newMarker.addTo(map).bindPopup(tag);
        }
    );
}

function fit2GisMapMarkers(){
    DG.then(function() {
            markersBounds = undefined;
            if (markers.length > 1) {
                markersBounds = DG.latLngBounds(markers[0], markers[1]);
                for (var key in markers){
                    markersBounds.extend(markers[key].getLatLng());
                }
                map.fitBounds(markersBounds);
            }
            else if(markers.length === 1){
                map.panTo(markers[0].getLatLng());
            }
        }
    );
}

function remove2GisMapMarkers(){
    DG.then(function(){
            if (markers === undefined) {
                markers = [];
            }
            else{
                for (var key in markers){
                    markers[key].removeFrom(map);
                }
                markers.length = 0;
            }
            markersBounds = undefined;
        }
    );
}

function set2GisMapTrack(coordinates){
    DG.then(function(){
        if (track != undefined){
            track.removeFrom(map);
            track.setLatLngs(coordinates);
            track.addTo(map);
            map.fitBounds(track.getBounds());
        }
        else{
            track = DG.polyline(coordinates, {color:'blue'});
            track.addTo(map);
        }
    });
}

function add2GisMapTracks(coordinates){
    DG.then(function(){
            if (tracks === undefined) {
                tracks = [];
            }
            var newTrack = DG.polyline(coordinates, {color: getRandomColor(4)});
            tracks.push(newTrack);
            newTrack.addTo(map);
        }
    );
}

function fit2GisMapTracks(){
    DG.then(function() {
            tracksBounds = undefined;
            if (tracks.length > 0) {
                tracksBounds = tracks[0].getBounds();
                for (var key in tracks){
                    tracksBounds.extend(tracks[key].getBounds());
                }
                map.fitBounds(tracksBounds);
            }
        }
    );
}

function remove2GisMapTracks(){
    DG.then(function(){
            if (tracks === undefined) {
                tracks = [];
            }
            else{
                for (var key in tracks){
                    tracks[key].removeFrom(map);
                }
                tracks.length = 0;
            }
            tracksBounds = undefined;
        }
    );
}

function getRandomColor(brightness) {
    var letters = '0123456789ABCDEF';
    var color = '#';
    var randomWidth = 6;
    randomWidth += brightness;
    randomWidth = (randomWidth > 16)? 16 : randomWidth;
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * randomWidth)];
    }
    return color;
}
