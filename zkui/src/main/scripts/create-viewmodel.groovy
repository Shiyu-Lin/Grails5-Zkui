// The description method will provide information of the usage of the script
// when the user checks "grails help"
description("Generate a viewModel with a given name"){
    usage "grails create-viewmodel [VIEWMODEL NAME]"
    argument name:"viewmodel name", description:"name of the viewmodel"
}

