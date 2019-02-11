 private fun getExternalImagesPath(context: Context): ArrayList<String> {
        return getImagesPathFromUri(context, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    }

    private fun getInternalImagesPath(context: Context): ArrayList<String> {
        return getImagesPathFromUri(context, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI)
    }

    private fun getImagesPathFromUri(context: Context, uri: Uri): ArrayList<String> {
        val cursor: Cursor?
        val column_index_data: Int
        val listOfAllImages = ArrayList<String>()
        var absolutePathOfImage: String
        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        cursor = context.contentResolver.query(uri, projection, null, null, null)
        if (cursor != null) {
            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index_data)
                listOfAllImages.add(absolutePathOfImage)
            }
            cursor.close()
        }
        return listOfAllImages
    }
