var data;
var i = 0
//var size = data.length;
var playpause = true;
var myInterval;
$(document).ready(function(){
                  
                  $.getJSON('slideData.json',function(d){
                            console.log(d);
                            data=d;
                            });
                  
                  });


function previousSlide() {
    
    i--;
    if(i < 0){
        i = data.slides.length-1;
    }
    document.getElementById("slideImg").setAttribute("src", "img/" + data.slides[i].image_file_name + "");
    document.getElementById("caption").innerHTML = data.slides[i].caption;
    
}

function startSlideShow(){
    
    nextSlide();
    setInterval("displaySlideShow()", 3000);
    
}

function setPlayOrPause(){
    playpause = !playpause;
    if (playpause){
        
        $("#playButton").show();
        $("#pauseButton").hide()
        clearInterval(myInterval);
    }
    else{
        
        $("#playButton").hide();
        $("#pauseButton").show();
        
        myInterval = setInterval("nextSlide()", 3000);
    }
    
}

function nextSlide(){
    i++;
    if (i == data.slides.length){
        i=0;
    }
    document.getElementById("slideImg").setAttribute("src", "img/" + data.slides[i].image_file_name + "");
    document.getElementById("caption").innerHTML = data.slides[i].caption;
    
}

function initSlideShow(){
    document.getElementById("title").innerHTML = data.title;
   document.getElementById("slideImg").setAttribute("src", "img/" + data.slides[0].image_file_name + "" );
    // document.getElementById("slideImg").setAttribute("src",data.slides[0].image_path + "" +data.slides[0].image_file_name + "" );
    $("#pauseButton").hide()
    document.getElementById("caption").innerHTML = data.slides[0].caption;
    
}

function getValueOfPlayPause(){
    return playpause;
}

