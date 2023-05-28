variable "folder_name" {
    type = string
    description = "name of the folder which should be created"
}

locals {
    current_time = formatdate("YYYYMMDDhhmmss", timestamp())
    folder_name_with_time = "${var.folder_name}___${local.current_time}"
}