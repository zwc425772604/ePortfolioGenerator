    var data;
	$(document).ready(function(){
      $.getJSON('./data/1stPage.json', function(d){
      	console.log(d);
      	data = d;
      });

	});

function initPortfolio() {

var portfolioDataFile = "./data/1stPage.json";
loadData(portfolioDataFile);
}

function loadData(jsonFile){
	$.getJSON(jsonFile, function(json){
      loadStudentName(json);
      if (containElement('bannerImagePath')){
          addBannerImage(json);
      }
      if (containElement('videoType')){
      initVideo(json);
  }
  if (containElement('p1')){
      addTextComponent(json);
  }
      if (containElement('imagePath')){
      addImageComponent(json);
  }
      if (containElement('h1')){
      addHeader(json);
  }
   
            addLink();
   
   
    if(containElement('List')){
  addListComp(json);
    }

	});
}
function addLink(){
    var a = document.createElement('a');
    a.setAttribute('href', "http://www3.cs.stonybrook.edu/~cse219/");
    a.innerHTML = "Google";
    document.getElementById("InlinedHyperlinks").appendChild(a);
}

function addListComp(portfolioData){
    var numberList = document.getElementById("unorderList");
    for (var i = 0; i <data.pagesCon[2].List.length; i++){
        var newNumberListItem = document.createElement("li");
        var numberListValue = document.createTextNode(data.pagesCon[2].List[i]);
        newNumberListItem.appendChild(numberListValue);
        numberList.appendChild(newNumberListItem);
    }
}
function addHeader(portfolioData){

   var para = document.createElement("h1");
   var node = document.createTextNode(data.pagesCon[2].h1);
   para.appendChild(node);
   var element = document.getElementById("Header");
   element.appendChild(para);

    
}



function addVideo(portfolioData){
    var vid = document.createElement("video");

var sourceMP4 = document.createElement("source");
sourceMP4.type = data.pagesCon[2].videoType;
sourceMP4.src = data.pagesCon[2].videoPath;

vid.appendChild(sourceMP4);
var vidElement = document.getElementById("Videos");
vidElement.appendChild(vid);
vid.controls = true;

}

function loadStudentName(portfolioData){
	document.getElementById("name").innerHTML = data.name;
}

function addBannerImage(portfolioData) {
    
    
var img = document.createElement("img");
img.setAttribute('src', data.pagesCon[2].bannerImagePath);
var imgElement = document.getElementById("BannerImage");
imgElement.appendChild(img);



}

function addTextComponent() {
var para = document.createElement("p");
var node = document.createTextNode(data.pagesCon[2].p1);
para.appendChild(node);
var element = document.getElementById("Paragraphs");
element.appendChild(para);

}

function containElement(typeElement){
      return data.pagesCon[2].hasOwnProperty(typeElement);
  }
function addImageComponent(portfolioData){
    var img = document.createElement("img");
    img.setAttribute('src', data.pagesCon[2].imagePath);
    var element = document.getElementById("Images");
    element.appendChild(img);
    
}
