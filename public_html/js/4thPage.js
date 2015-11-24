
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
            loadStudentName();
      if (containElement('bannerImagePath')){
      addBannerImage(json);
  }
      if (containElement('videoType')){
      addVideo(json);
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
      if(containElement('List')){
       addListComp(json);
      }
	});
}
function containElement(typeElement){
      return data.pagesCon[3].hasOwnProperty(typeElement);
  }
 function addVideo(portfolioData){
    var vid = document.createElement("video");
    var sourceMP4 = document.createElement("source");
    sourceMP4.type = data.pagesCon[3].videoType;
    sourceMP4.src = data.pagesCon[3].videoPath;
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
// img.setAttribute('src', "ePortfolioBannerImage.png");
 img.setAttribute('src', data.pagesCon[3].bannerImagePath);
var imgElement = document.getElementById("BannerImage");
imgElement.appendChild(img);

}

function addTextComponent() {
var para = document.createElement("p");
var node = document.createTextNode(data.pagesCon[3].p1);
para.appendChild(node);
var element = document.getElementById("Paragraphs");
element.appendChild(para);

}
function addImageComponent(portfolioData){
    var img = document.createElement("img");
    img.setAttribute('src', data.pagesCon[3].imagePath);
    img.height = data.pagesCon[3].imageHeight;
    img.width = data.pagesCon[3].imageWidth;
    var element = document.getElementById("Images");
    element.appendChild(img);
    
}

function addListComp(portfolioData){
//    var list = document.createElement("unorderList");
//    var List = document.getElementById("List");
//    List.appendChild(list);
    

//    var nList = document.getElementById("List");
      var numberList = document.getElementById("unorderList");
    for (var i = 0; i <data.pagesCon[3].List.length; i++){
        var newNumberListItem = document.createElement("li");
        var numberListValue = document.createTextNode(data.pagesCon[3].List[i]);
        newNumberListItem.appendChild(numberListValue);
        numberList.appendChild(newNumberListItem);
    }
//    nList.appendChild(numberList);
}

function addHeader(portfolioData){
       var para = document.createElement("h1");
   var node = document.createTextNode(data.pagesCon[3].h1);
   para.appendChild(node);
   var element = document.getElementById("Header");
   element.appendChild(para);
   }

