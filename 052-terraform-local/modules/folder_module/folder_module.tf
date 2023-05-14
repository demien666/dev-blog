resource "null_resource" "create_folder" {

    provisioner "local-exec" {
      command = "mkdir ${var.folder_name}"
    }

    triggers = {
        created_folder_name = var.folder_name
    }

    provisioner "local-exec" {
      command = "rmdir ${self.triggers.created_folder_name}"
      when = destroy
    }

}