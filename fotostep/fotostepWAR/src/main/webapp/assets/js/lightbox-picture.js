function openbox(formtitle, iduser, idPic)
{
    // Set l'image
    document.getElementById('view-photo-content:view-pic:viewed-image').setAttribute('src',
        'fotostep/images?UserId=' + iduser + '&PictureId='+idPic);
    var box = document.getElementById('box');
    document.getElementById('shadowing').style.display='block';

    var btitle = document.getElementById('boxtitle');
    btitle.innerHTML = formtitle;
    box.style.display='block';
}


// Close the lightbox

function closebox()
{
    document.getElementById('box').style.display='none';
    document.getElementById('shadowing').style.display='none';
}



