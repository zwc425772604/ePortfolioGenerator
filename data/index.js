
      var data;
	$(document).ready(function(){
      $.getJSON('index.json', function(d){
      	console.log(d);
      	data = d;
      });

	});

function initPortfolio() {

var portfolioDataFile = "index.json";
loadData(portfolioDataFile);
}

function loadData(jsonFile){
	$.getJSON(jsonFile, function(json){
      loadStudentName(json);
      if (containElement('bannerImagePath')){
      addBannerImage(json);
  }
      if (containElement('videoType')){
      addVideo(json);
  }
      if (containElement('page_paragraph')){
      addTextComponent(json);
  }
      if (containElement('imagePath')){
      addImageComponent(json);
  }
      if (containElement('page_header')){
      addHeader(json);
  }
      if(containElement('page_list')){
      addListComp(json);
      }
	});
}
   function addHeader(portfolioData){
       var para = document.createElement("h1");
   var node = document.createTextNode(data.pagesCon[0].h1);
   para.appendChild(node);
   var element = document.getElementById("Header");
   element.appendChild(para);
   }
 
function addVideo(portfolioData){
    var vid = document.createElement("video");

var sourceMP4 = document.createElement("source");
sourceMP4.type = data.pagesCon[0].videoType;
sourceMP4.src = data.pagesCon[0].videoPath;

vid.appendChild(sourceMP4);
var vidElement = document.getElementById("Videos");
vidElement.appendChild(vid);
vid.controls = true;

}

function loadStudentName(portfolioData){
	document.getElementById("name").innerHTML = data.student_name;
}

function addBannerImage(portfolioData) {
var img = document.createElement("img");
// img.setAttribute('src', "ePortfolioBannerImage.png");
 img.setAttribute('src', data.pagesCon[0].bannerImagePath);
var imgElement = document.getElementById("BannerImage");
imgElement.appendChild(img);

}

function addTextComponent(portfolioData) {
var para = document.createElement("p");
var node = document.createTextNode(data.pagesCon[0].p1);
para.appendChild(node);
var element = document.getElementById("Paragraphs");
element.appendChild(para);

}

function addImageComponent(portfolioData){
    var img = document.createElement("img");
    img.setAttribute('src', data.pagesCon[0].imagePath);
    var element = document.getElementById("Images");
    element.appendChild(img);
    
}

function addListComp(portfolioData){
    var numberList = document.getElementById("unorderList");
    for (var i = 0; i <data.pagesCon[0].List.length; i++){
        var newNumberListItem = document.createElement("li");
        var numberListValue = document.createTextNode(data.pagesCon[0].List[i]);
        newNumberListItem.appendChild(numberListValue);
        numberList.appendChild(newNumberListItem);
    }
}
function containElement(typeElement){
      return data.pagesCon[0].hasOwnProperty(typeElement);
  }
 

