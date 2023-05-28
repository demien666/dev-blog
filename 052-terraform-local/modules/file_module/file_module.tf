resource "null_resource" "create_file" {

  provisioner "local-exec" {
    command = "echo ${var.file_content} >> ${var.folder_name}/${var.file_name}"
  }

  triggers = {
      created_file_path = "${var.folder_name}/${var.file_name}"
  }

  provisioner "local-exec" {
    command = "rm ${self.triggers.created_file_path}"
    when = destroy
  }

}