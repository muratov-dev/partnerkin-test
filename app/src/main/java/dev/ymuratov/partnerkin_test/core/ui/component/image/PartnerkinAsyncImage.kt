package dev.ymuratov.partnerkin_test.core.ui.component.image

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest

@Composable
fun PartnerkinAsyncImage(
    modifier: Modifier = Modifier,
    data: Any? = null,
    contentDescription: String? = null,
    @DrawableRes placeholder: Int? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(data).crossfade(true).build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            is AsyncImagePainter.State.Loading -> ImageShimmer(modifier = modifier)
            else -> ImagePlaceholder(placeholder = placeholder)
        }
    }
}