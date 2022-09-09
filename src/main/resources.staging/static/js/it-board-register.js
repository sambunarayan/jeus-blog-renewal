var main = {
    colorSelect : function() {
        let selected = $("#colorSelect").val();
        console.log("selected value -> " + selected);
        Array.from(document.querySelector("#colorSelect").options).forEach(function(option_element) {
            let option_value = option_element.value;
            console.log("value -> " + option_value);
            if (option_element.value == selected) {
                option_element.selected = true;
                console.log(option_element.value + " is selected");
            }
        });
    }
}
main.colorSelect();

function buttonClick(){
  let selectOptions = colorSelect.selectedOptions;
  $('#colorSelect').removeClass(currColor);
  for (let i = 0 ; i < selectOptions.length ; i++){
    let color = selectOptions[i].value.replace('text', 'bg');
    $('#colorSelect').addClass(color);
    currColor = color;
  }
}

let currColor = "";
let colorSelect = document.getElementById('colorSelect');
colorSelect.addEventListener('click', buttonClick);
