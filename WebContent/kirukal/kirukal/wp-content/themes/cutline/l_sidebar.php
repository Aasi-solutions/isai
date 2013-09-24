<div id="l_sidebar">
	<ul class="sidebar_list">
		<?php if (!function_exists('dynamic_sidebar') || !dynamic_sidebar(1)) : ?>
		<li class="widget">
			<h2>Categories</h2>
			<ul>
				<?php wp_list_cats('sort_column=name'); ?>
			</ul>
		</li>
		<li class="widget">
			<h2>Archives</h2>
			<ul>
				<?php wp_get_archives('type=monthly'); ?>
			</ul>
		</li>
		<li class="widget">
			<h2>Admin</h2>
			<ul>
				<?php wp_register(); ?>
				<li><?php wp_loginout(); ?></li>
				<li><a href="http://www.wordpress.org/">WordPress</a></li>
				<?php wp_meta(); ?>
				<li><a href="http://validator.w3.org/check?uri=referer">XHTML</a></li>
			</ul>
		</li>
		<?php endif; ?>
	</ul>
</div>