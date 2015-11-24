
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
      if (containElement('Hyperlink')){
          addLink(json);
      }
      if(containElement('List')){
      addListComp(json);
      }
      
	});
}
 function addLink(portfolioData){
     var str = data.pagesCon[4].p1;
//     var str = "CSE IS FUN";
     var res = str.substring(data.pagesCon[4].firstIndex, data.pagesCon[4].lastIndex + 1);
//     var a = document.createElement('a');
//     a.setAttribute('href', data.pagesCon[4].url);
//     a.innerHTML = res + "is fun";
     var result = res.link(data.pagesCon[4].url);
     document.getElementById("InlinedHyperlinks").innerHTML = result + "is fun";
     
//     document.getElementById("InlinedHyperlinks").appendChild(a);
//     document.getElementById("InlinedHyperlinks").innerHTML = res;
 }

function addVideo(portfolioData){
    var vid = document.createElement("video");

var sourceMP4 = document.createElement("source");
sourceMP4.type = data.pagesCon[4].videoType;
sourceMP4.src = data.pagesCon[4].videoPath;

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
 img.setAttribute('src', data.pagesCon[4].bannerImagePath);
var imgElement = document.getElementById("BannerImage");
imgElement.appendChild(img);

}

function addTextComponent() {
var para = document.createElement("p");
var node = document.createTextNode(data.pagesCon[4].p1);
para.appendChild(node);
var element = document.getElementById("Paragraphs");
element.appendChild(para);
//document.write('<a href = data.pagesCon[4].url "> CSE </a>');
}

function containElement(typeElement){
      return data.pagesCon[4].hasOwnProperty(typeElement);
  }
  
  function addHeader(portfolioData){
       var para = document.createElement("h1");
   var node = document.createTextNode(data.pagesCon[4].h1);
   para.appendChild(node);
   var element = document.getElementById("Header");
   element.appendChild(para);
   }
   
   function addImageComponent(portfolioData){
    var img = document.createElement("img");
    img.setAttribute('src', data.pagesCon[4].imagePath);
    var element = document.getElementById("Images");
    element.appendChild(img);
    
}
function addListComp(portfolioData){
    var numberList = document.getElementById("unorderList");
    for (var i = 0; i <data.pagesCon[4].List.length; i++){
        var newNumberListItem = document.createElement("li");
        var numberListValue = document.createTextNode(data.pagesCon[4].List[i]);
        newNumberListItem.appendChild(numberListValue);
        numberList.appendChild(newNumberListItem);
    }
}
