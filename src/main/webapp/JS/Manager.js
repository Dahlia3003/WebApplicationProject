var pro = document.querySelector('.Product');
selected(pro, 'Product');

function selected(self, name) {
    var buttonDiv = document.querySelectorAll(".button");
    buttonDiv.forEach(function(div) {
        div.style.backgroundColor="#dadadb";
        div.style.width="100%";
    });
    self.style.backgroundColor="#F5F5F7";
    self.style.width="101%";
    var pro = document.querySelector('.Product');
    var order = document.querySelector('.Order');
    var acc = document.querySelector('.Account');
    if (name=="Product")
    {
        pro.style.display='block';
        order.style.display='none';
        acc.style.display='none';
    }
    if (name=="Order")
    {
        pro.style.display='none';
        order.style.display='flex';
        acc.style.display='none';
    }
    if (name=="Account")
    {
        pro.style.display='none';
        order.style.display='none';
        acc.style.display='flex';
    }
}
function productClicked(self)
{
    $.ajax({
        url: 'productmanage?id='+self.id,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            fillData(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error:', textStatus, errorThrown);
        }
    });
}
function fillData(product){
    var idlabel = document.getElementById('prid');
    idlabel.innerText="Product ID: "+product[0];
    idlabel.value=product[0];
    const inputIds = ['id', 'brand', 'line', 'name', 'price', 'vari', 'link', 'available', 'chip', 'rom', 'scr', 'cam', 'pin', 'connect'];
    for (let i = 0; i < inputIds.length && i < product.length; i++) {
        const inputId = inputIds[i];
        const inputValue = product[i];
        const inputElement = document.getElementById(inputId);
        if (inputElement) {
            inputElement.value = inputValue;
        }
    }
    var pic = document.getElementById('pic');
    pic.src=product[6];
    var avai = document.getElementById('avai');
    if (product[7]=="true")
        avai.checked=true;
    else
        avai.checked=false;
    var tags = document.querySelectorAll(".tag");
    tags.forEach(function(tag) {
       for (let i = 13; i < product.length; i++) {
           if (tag.value==product[i]) {
               tag.checked = true;
               break;
           }
           tag.checked=false;
       }
    });
    var noti = document.getElementById('noti');
    noti.innerText='';
    var bt = document.getElementById('bt');
    bt.value="update";
    bt.innerText='Update';
}
function buttonCLick(self)
{
    var idlabel = document.getElementById('prid');
    var brand = document.getElementById('brand');
    var line = document.getElementById('line');
    var name = document.getElementById('name');
    var price = document.getElementById('price');
    var vari = document.getElementById('vari');
    var imagelink = document.getElementById('link');
    var chip = document.getElementById('chip');
    var rom = document.getElementById('rom');
    var scr = document.getElementById('scr');
    var cam = document.getElementById('scr');
    var pin = document.getElementById('scr');
    var connect = document.getElementById('connect');
    var avai = document.getElementById('avai');
    var available='true';
    if (!avai.checked)
        available='false'
    var des=chip.value+'/'+rom.value+'/'+scr.value+'/'+cam.value+'/'+pin.value+'/'+connect.value
                +'|';
    var tags = document.querySelectorAll(".tag");
    tags.forEach(function(tag) {
        if (tag.checked)
            des+=tag.value;
    });
    var pic = document.getElementById('pic');
    var url = 'productupdate?action=' + encodeURIComponent(self.value) +
        '&id=' + encodeURIComponent(idlabel.value) +
        '&brand=' + encodeURIComponent(brand.value) +
        '&line=' + encodeURIComponent(line.value) +
        '&name=' + encodeURIComponent(name.value) +
        '&price=' + encodeURIComponent(price.value) +
        '&vari=' + encodeURIComponent(vari.value) +
        '&image=' + encodeURIComponent(imagelink.value) +
        '&avai=' + encodeURIComponent(available) +
        '&speci=' + encodeURIComponent(des);

    $.ajax({
        url: url,
        type: 'GET',
        success: function() {
            var noti = document.getElementById('noti');
            noti.innerText=self.value+' successfully!';
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error:', textStatus, errorThrown);
        }
    });
}
function addNew(){
    var idlabel = document.getElementById('prid');
    var brand = document.getElementById('brand');
    var line = document.getElementById('line');
    var name = document.getElementById('name');
    var price = document.getElementById('price');
    var vari = document.getElementById('vari');
    var imagelink = document.getElementById('link');
    var chip = document.getElementById('chip');
    var rom = document.getElementById('rom');
    var scr = document.getElementById('scr');
    var cam = document.getElementById('cam');
    var pin = document.getElementById('pin');
    var connect = document.getElementById('connect');
    var avai = document.getElementById('avai');
    idlabel.innerText='';
    brand.value='';
    line.value='';
    name.value='';
    price.value='';
    vari.value='';
    chip.value='';
    rom.value='';
    imagelink.value='';
    scr.value='';
    cam.value='';
    pin.value='';
    connect.value='';
    avai.checked=false;
    brand.value='';
    var tags = document.querySelectorAll(".tag");
    tags.forEach(function(tag) {
        tag.checked=false;
    });
    var bt = document.getElementById('bt');
    bt.value="add";
    bt.innerText='Add';
    var pic = document.getElementById('pic');
    pic.src='';
}