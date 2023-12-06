function selected(self) {
    var buttonDiv = document.querySelectorAll(".button");
    buttonDiv.forEach(function(div) {
        div.style.backgroundColor="#dadadb";
        div.style.width="100%";
    });
    self.style.backgroundColor="#F5F5F7";
    self.style.width="101%";
}