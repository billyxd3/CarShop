function appHandleError(err) {
    console.log(err);
    if (err.responseJson) {
        alert(err.responseJson.message)
    } else {
        alert('something went wrong')
    }
}

function appGetBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });
}


