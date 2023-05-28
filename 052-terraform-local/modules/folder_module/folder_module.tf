resource "null_resource" "create_folder" {

  provisioner "local-exec" {
    command = "mkdir ${local.folder_name_with_time}"
  }

  triggers = {
      created_folder_name = "${local.folder_name_with_time}"
  }

  provisioner "local-exec" {
    command = "rmdir ${self.triggers.created_folder_name}"
    when = destroy
  }

}