package hudson.plugins.sloccount;

import hudson.Extension;
import hudson.model.AbstractBuild;
import org.jenkinsci.plugins.codehealth.LinesOfCode;
import org.jenkinsci.plugins.codehealth.LinesOfCodeDescriptor;
import org.jenkinsci.plugins.codehealth.LinesOfCodeProvider;
import org.jenkinsci.plugins.database.PerItemDatabaseDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * @author Michael Prankl
 */
public class SloccountLoCProvider extends LinesOfCodeProvider {

    @DataBoundConstructor
    public SloccountLoCProvider() {
    }

    @Override
    public LinesOfCode getLOC(AbstractBuild<?, ?> build) {
        final SloccountBuildAction action = build.getAction(SloccountBuildAction.class);
        if (action != null) {
            int fileCount = action.getResult().getStatistics().getFileCount();
            int lineCount = action.getResult().getStatistics().getLineCount();
            return new LinesOfCode(lineCount, fileCount);
        }
        return null;
    }

    @Override
    public String getOrigin() {
        return "sloccount";
    }

    @Override
    public LinesOfCodeDescriptor getDescriptor() {
        return super.getDescriptor();
    }

    @Extension
    public static class DescriptorImpl extends LinesOfCodeDescriptor {

        @Override
        public String getDisplayName() {
            return "sloccount";
        }

    }
}
