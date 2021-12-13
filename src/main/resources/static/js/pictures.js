const picturesContainer = document.getElementById('pictureContainer')
const allPictures = []

const displayPictures = (pictures) => {
    picturesContainer.innerHTML = pictures.map(
        (p) => {
            return viewPicture(p)
        }
    ).join('')
}

async function handlePicture(event) {
    event.preventDefault();

    const form = event.currentTarget;
    const url = form.action;
    const formData = new FormData(form);

    try {
        const responseData = await postFormDataAsJson({url, formData});

        picturesContainer.insertAdjacentHTML("afterbegin", viewPicture(responseData));
        form.reset();

    } catch (error) {

        let errorObj = JSON.parse(error.message);

        if (errorObj.fieldWithErrors) {
            errorObj.fieldWithErrors.forEach(
                e => {
                    let elementWithError = document.getElementById(e);
                    if (elementWithError) {
                        elementWithError.classList.add("is-invalid");
                    }
                }
            )
        }
    }
}

function viewPicture(p) {
    let pictureHtml =
        `<br><div class="card" id="pictureContainer-${p.picture}"><div class="card-body">`
    pictureHtml +=
        `<br><h4>${p.author} (${p.created})</h4><br/>`
    pictureHtml +=
        `<p>${p.title}</p>`
    pictureHtml +=
        `<a href="${p.url}"><img src="${p.url}" class="img-fluid" alt="Responsive image"></a>`
    pictureHtml +=
        `<br><div>
<form action="/reports/${reportId}/report-details/pictures/all/delete"
          method="delete">
      <input type="hidden" name="public_id" value="${p.publicId}"/>
      <br>
      <input class="btn btn-outline-danger" type="submit" value="Delete"/>
 </form>
</div></div></div><br>`

    return pictureHtml
}

fetch(`http://localhost:8080/reports/${reportId}/report-details/pictures/all`)
    .then(response => response.json()).then(data => {
    for (let picture of data) {
        allPictures.push(picture)
    }
    displayPictures(allPictures)
})