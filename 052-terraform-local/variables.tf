variable "folder_1" {
    type = string
    description = "name of the first folder"
    default = "TF_FOLDER_1"
}

variable "file_1" {
    type = string
    description = "name of the file in the first folder"
    default = "tf_file_1.txt"
}

variable "file_1_content" {
    type = string
    description = "content of the file in the first folder"
    default = "Hello world!"
}

variable "folder_2" {
    type = string
    description = "name of the second folder"
    default = "TF_FOLDER_2"
}